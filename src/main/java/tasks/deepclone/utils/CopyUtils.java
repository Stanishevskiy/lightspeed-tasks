package tasks.deepclone.utils;

import tasks.deepclone.exception.DeepCloneException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class CopyUtils {

    private CopyUtils() {}

    private static final Map<Object, Object> visited = new IdentityHashMap<>();

    public static <T> T deepCopy(T obj) {
        if (obj == null) {
            return null;
        }

        if (visited.containsKey(obj)) {
            return (T) visited.get(obj);
        }

        return (T) switch (obj) {
            case Map<?, ?> map -> deepCopyMap(map);
            case Collection<?> col -> deepCopyCollection(col);
            case Array arr -> deepCopyArray(arr);
            case Enum<?> en -> en;
            case String s -> s;
            case Number n -> n;
            case Boolean b -> b;
            default -> deepCopyObject(obj);
        };
    }

    private static Map<?, ?> deepCopyMap(Map<?, ?> orig) {
        Map<Object, Object> copy;
        Class<?> clazz = orig.getClass();
        if (clazz.equals(HashMap.class)) {
            copy = new HashMap<>();
        } else if (clazz.equals(LinkedHashMap.class)) {
            copy = new LinkedHashMap<>();
        } else if (SortedMap.class.isAssignableFrom(clazz)) {
            copy = new TreeMap<>();
        } else {
            copy = new HashMap<>();
        }

        for (Map.Entry<?, ?> entry : orig.entrySet()) {
            copy.put(deepCopy(entry.getKey()),
                    deepCopy(entry.getValue()));
        }

        visited.put(orig, copy);
        return copy;
    }

    private static Collection<?> deepCopyCollection(Collection<?> orig) {
        Collection<Object> copy;
        Class<?> clazz = orig.getClass();
        if (clazz.equals(PriorityQueue.class)) {
            copy = new PriorityQueue<>();
        } else if (clazz.equals(ArrayDeque.class)) {
            copy = new ArrayDeque<>();
        } else if (Queue.class.isAssignableFrom(clazz)) {
            copy = new PriorityQueue<>();
        } else if (clazz.equals(HashSet.class)) {
            copy = new HashSet<>();
        } else if (clazz.equals(LinkedHashSet.class)) {
            copy = new LinkedHashSet<>();
        } else if (clazz.equals(TreeSet.class)) {
            copy = new TreeSet<>();
        } else if (Set.class.isAssignableFrom(clazz)) {
            copy = new HashSet<>();
        } else if (clazz.equals(ArrayList.class)) {
            copy = new ArrayList<>();
        } else if (clazz.equals(LinkedList.class)) {
            copy = new LinkedList<>();
        } else {
            copy = new ArrayList<>();
        }

        for (Object elm : orig) {
            copy.add(deepCopy(elm));
        }

        visited.put(orig, copy);
        return copy;
    }

    private static Object deepCopyArray(Object orig) {
        Class<?> componentType = orig.getClass().getComponentType();
        int length = Array.getLength(orig);
        Object copy = Array.newInstance(componentType, length);

        for (int i = 0; i < length; i++) {
            Array.set(copy, i, deepCopy(Array.get(orig, i)));
        }

        visited.put(orig, copy);
        return copy;
    }

    private static <T> Object deepCopyObject(T orig) {
        Class<?> clazz = orig.getClass();
        Object copy;
        try {
            copy = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new DeepCloneException("Error while creating a copy of object\n", e);
        }

        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    try {
                        Object fieldValue = field.get(orig);
                        field.set(copy, deepCopy(fieldValue));
                    } catch (IllegalAccessException e) {
                        throw new DeepCloneException("Error while creating a copy of field\n", e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }

        return copy;
    }

}

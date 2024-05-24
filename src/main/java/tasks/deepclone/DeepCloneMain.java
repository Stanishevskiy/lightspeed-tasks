package tasks.deepclone;

import tasks.deepclone.dto.Employee;
import tasks.deepclone.dto.Man;
import tasks.deepclone.dto.Project;
import tasks.deepclone.dto.enums.ProgramLanguage;
import tasks.deepclone.utils.CopyUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeepCloneMain {

    public static void main(String[] args) {
        var man1 = new Man("Jack", 25, List.of("The Amber Spyglass", "Austerlitz"));
        var man2 = new Man("Mike", 24, List.of("Wolf Hall"));
        var man3 = new Man("Nicole", 27, List.of("Gilead", "Secondhand Time", "Never Let Me Go"));

        var copyMan1 = CopyUtils.deepCopy(man1);
        var copyMan2 = CopyUtils.deepCopy(man2);
        var copyMan3 = CopyUtils.deepCopy(man3);

        System.out.println("Orig man1:\n" + man1);
        System.out.println("Copy man1:\n" + copyMan1);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Orig man2:\n" + man2);
        System.out.println("Copy man2:\n" + copyMan2);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Orig man3:\n" + man3);
        System.out.println("Copy man3:\n" + copyMan3);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println();

        //--------------------------------------------------------------------------------------------------------------

        var employee1 = new Employee("Jack", 38, Set.of("jack@fin.mail", "jack@med.mail"));
        var employee2 = new Employee("Kile", 27, Set.of("cries@fin.mail"));
        var employee3 = new Employee("Jessy", 24, Set.of("jessy@fin.mail"));
        var employee4 = new Employee("Nicole", 28, Set.of("nicole@fin.mail"));
        var employee5 = new Employee("Nick", 31, Set.of("nick@fin.mail"));

        var project1 = new Project("Project 1", "Fintech", ProgramLanguage.JAVA);
        var project2 = new Project("Project 2", "Medicine", ProgramLanguage.PYTHON);

        employee1.setProjects(List.of(project1, project2));
        employee1.setPositionToSubordinates(Map.of("Project Owner", List.of(employee2, employee3, employee4),
                "CTO", List.of(employee5)));
        employee2.setProjects(List.of(project1));
        employee2.setPositionToSubordinates(Map.of("Middle Java Developer", List.of()));
        employee3.setProjects(List.of(project1));
        employee3.setPositionToSubordinates(Map.of("Junior Java Developer", List.of()));
        employee4.setProjects(List.of(project1));
        employee4.setPositionToSubordinates(Map.of("Senior Java Developer", List.of()));
        employee5.setProjects(List.of(project2));
        employee5.setPositionToSubordinates(Map.of("Senior Python Developer", List.of()));

        var copyEmployee1 = CopyUtils.deepCopy(employee1);
        var copyEmployee2 = CopyUtils.deepCopy(employee2);
        var copyEmployee3 = CopyUtils.deepCopy(employee3);
        var copyEmployee4 = CopyUtils.deepCopy(employee4);
        var copyEmployee5 = CopyUtils.deepCopy(employee5);

        System.out.println("Orig employee1:\n" + employee1);
        System.out.println("Copy employee1:\n" + copyEmployee1);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Orig employee2:\n" + employee2);
        System.out.println("Copy employee2:\n" + copyEmployee2);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Orig employee3:\n" + employee3);
        System.out.println("Copy employee3:\n" + copyEmployee3);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Orig employee4:\n" + employee4);
        System.out.println("Copy employee4:\n" + copyEmployee4);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Orig employee5:\n" + employee5);
        System.out.println("Copy employee5:\n" + copyEmployee5);
        System.out.println("-----------------------------------------------------------------------------------------");
    }
}

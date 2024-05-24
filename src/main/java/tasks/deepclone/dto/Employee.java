package tasks.deepclone.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Employee {
    private String name;
    private int age;
    private Set<String> mails;
    private List<Project> projects;
    private Map<String, List<Employee>> positionToSubordinates;

    public Employee() { }

    public Employee(String name, int age, Set<String> mails) {
        this.name = name;
        this.age = age;
        this.mails = mails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<String> getMails() {
        return mails;
    }

    public void setMails(Set<String> mails) {
        this.mails = mails;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Map<String, List<Employee>> getPositionToSubordinates() {
        return positionToSubordinates;
    }

    public void setPositionToSubordinates(Map<String, List<Employee>> positionToSubordinates) {
        this.positionToSubordinates = positionToSubordinates;
    }

    @Override
    public String toString() {
        return """
				Employee
				 name: %s,
				 age: %d,
				 mails: %s,
				 projects: %s,
				 positionToSubordinates: %s""".formatted(name, age, mails, projects, positionToSubordinates);
    }
}

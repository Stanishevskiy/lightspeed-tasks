package tasks.deepclone.dto;

import tasks.deepclone.dto.enums.ProgramLanguage;

public class Project {
    private String name;
    private String projectArea;
    private ProgramLanguage programLanguage;

    public Project() { }

    public Project(String name, String projectArea, ProgramLanguage programLanguage) {
        this.name = name;
        this.projectArea = projectArea;
        this.programLanguage = programLanguage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
    }

    public ProgramLanguage getProgramLanguage() {
        return programLanguage;
    }

    public void setProgramLanguage(ProgramLanguage programLanguage) {
        this.programLanguage = programLanguage;
    }

    @Override
    public String toString() {
        return """
				Project
				 name: %s,
				 projectArea: %s,
				 programLanguage: %s""".formatted(name, projectArea, programLanguage);
    }
}

package com.closa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProjectRequest {
    @NotBlank
    @NotNull
    private String projectout;
    @NotBlank
    @NotNull
    private String filein;

    public ProjectRequest() {
    }

    public String getProjectout() {
        return projectout;
    }

    public void setProjectout(String projectout) {
        this.projectout = projectout;
    }

    public String getFilein() {
        return filein;
    }

    public void setFilein(String filein) {
        this.filein = filein;
    }
}

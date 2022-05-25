package com.closa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReportRequest {
    @NotBlank
    @NotNull
    private String projectin;
    @NotBlank
    @NotNull
    private String fileout;

    public ReportRequest() {
    }

    public String getProjectin() {
        return projectin;
    }

    public void setProjectin(String projectin) {
        this.projectin = projectin;
    }

    public String getFileout() {
        return fileout;
    }

    public void setFileout(String fileout) {
        this.fileout = fileout;
    }
}

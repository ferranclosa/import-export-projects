package com.closa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Properties {
    @Value("${app.project.input}")
    private String inproject;
    @Value("${app.consolidated.file}")
    private String file;
    @Value("${app.build.protocol}")
    private String build;
    @Value("${app.project.output}")
    private String outproject;


    public Properties() {
        this.inproject = getInproject();
        this.file = getFile();
        this.build = getBuild();
        this.outproject = getOutproject();
    }

    public String getInproject() {
        return inproject;
    }

    @Value("${app.project.input}")
    public void setInproject(String inproject) {
        this.inproject = inproject;
    }

    public String getFile() {
        return file;
    }
    @Value("${app.consolidated.file}")
    public void setFile(String file) {
        this.file = file;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getOutproject() {
        return outproject;
    }

    public void setOutproject(String outproject) {
        this.outproject = outproject;
    }
}

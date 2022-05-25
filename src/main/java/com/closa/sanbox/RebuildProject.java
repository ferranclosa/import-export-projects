package com.closa.sanbox;

import com.closa.services.CreateProject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class RebuildProject {

    @Value("${app.project.input}")
    String file;
    @Value("${app.project.output}")
    String project;

    public void RebuildTheProject(){

        System.out.println("Executing Building Project from Consolidated Project TEXT...START");
        System.out.println("Input File is " + file);

        Path outpath = Path.of(project);
        Path inFile = Path.of(file);
        CreateProject cp = new CreateProject();
        try {
            cp.CreateTheProject(outpath, inFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Output Project is " + project);
        System.out.println("Executing Building Project from Consolidated Project TEXT...END");

    }
}

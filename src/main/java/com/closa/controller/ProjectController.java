package com.closa.controller;

import com.closa.dto.ProjectRequest;
import com.closa.dto.ProjectResponse;
import com.closa.dto.ReportRequest;
import com.closa.dto.ReportResponse;
import com.closa.services.CreateFile;
import com.closa.services.CreateProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping(value = "/api")
public class ProjectController {

    @Autowired
    CreateFile cf;

    @Autowired
    CreateProject cp;

    @PostMapping(value = "/buildReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReportResponse buildConsolidatedFile (@RequestBody ReportRequest iDto){
        ReportResponse oDto = new ReportResponse();

        try {
            Path inPath = Path.of(iDto.getProjectin());
            Path outFile = Path.of(iDto.getFileout());

            oDto = cf.CreateTheFile(inPath, outFile);
        } catch (IOException e) {
            e.printStackTrace();
            oDto.setResponseCode("KO");
            oDto.setResponseMessage(e.getLocalizedMessage());
        }
        return oDto;
    }

    @PostMapping(value = "/buildProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProjectResponse buildConsolidatedProject (@RequestBody ProjectRequest iDto){
        ProjectResponse oDto = new ProjectResponse();

        try {
            Path outPath = Path.of(iDto.getProjectout());
            Path inFile = Path.of(iDto.getFilein());
            oDto = cp.CreateTheProject(outPath, inFile);
        } catch (IOException e) {
            e.printStackTrace();
            oDto.setResponseCode("KO");
            oDto.setResponseMessage(e.getLocalizedMessage());
        }
        return oDto;



    }
}

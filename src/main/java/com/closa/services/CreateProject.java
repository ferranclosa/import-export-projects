package com.closa.services;

import com.closa.common.Constants;
import com.closa.common.Functions;
import com.closa.dto.ProjectResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class CreateProject {
    public ProjectResponse CreateTheProject(Path outPath, Path inFile) throws IOException {


        ProjectResponse oDto = new ProjectResponse();

        Functions f = new Functions();

        Path request = null;
        if (Files.exists(outPath)) {
            f.deleteFiles(outPath);
            f.deleteEmptyFolders(outPath);
            Files.deleteIfExists(outPath);
        }
        if (!Files.exists(outPath)) {
            Files.createDirectories(outPath);
        }
        ;

        /**
         * Get the Request from the header
         */
        try (var reader = Files.newBufferedReader(inFile, StandardCharsets.UTF_8)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                if ((currentLine != null) && (currentLine.indexOf(Constants.REQUEST_START) == 0)
                        && ((currentLine.contains(Constants.REQUEST_START) && (currentLine.contains(Constants.REQUEST_END))))) {
                    request = Path.of(currentLine.substring(currentLine.indexOf(Constants.REQUEST_START) + 9, currentLine.indexOf(Constants.REQUEST_END)));
                    break;
                }
            }
        }
        /**
         * Get the Path from the header
         */
        Path fileName = null;
        String filePath = null;
        Path newPath = null;
        try (var reader = Files.newBufferedReader(inFile, StandardCharsets.UTF_8)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                if ((currentLine != null) && (currentLine.indexOf(Constants.PATH_START) == 0)) {
                    filePath = currentLine.substring(currentLine.indexOf(Constants.PATH_START) + 6, currentLine.indexOf(Constants.PATH_END));
                    fileName = Path.of(filePath).getFileName();
                    newPath = Path.of(filePath.replace(request.toString(), outPath.toString()));
                    newPath = Path.of(String.valueOf(newPath.getParent()));
                    Files.createDirectories(newPath);
                    var writer = Files.newBufferedWriter(Paths.get(String.valueOf(newPath.resolve(fileName))), StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);

                    while ((currentLine = reader.readLine()) != null) {
                        if ((currentLine != null) && (currentLine.indexOf(Constants.CODE_START) == 0)) {
                            continue;
                        }
                        if ((currentLine != null) && (currentLine.indexOf(Constants.CODE_END) == 0)) {
                            writer.close();
                            break;
                        }
                        writer.write(currentLine);
                        writer.newLine();
                    }

                }
            }

        }
        oDto.setResponseCode("OK");
        oDto.setResponseMessage("Project was created in " + outPath.toString());
        return oDto;
    }
}
package com.closa.services;

import com.closa.common.Functions;
import com.closa.dto.ReportResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * This method will create a file containing all the source code for a given project. This file will have ALL
 * code from the project , plus a FEW tags that will allow for the file to be used by the CreateProject Method below
 * to rebuild the project based on this file
 */
@Component
public class CreateFile {
    public ReportResponse CreateTheFile (Path thePath, Path outFile) throws IOException {
        ReportResponse oDto = new ReportResponse();
        Files.deleteIfExists(outFile);

        var writer = Files.newBufferedWriter(outFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        writer.write("<REQUEST>" + thePath + "</REQUEST>");
        writer.newLine();
        Functions f = new Functions();

        List<Path> list = f.listPathFiles(thePath);
        for(Path ph : list){
            if(ph.getFileName().toString().equalsIgnoreCase(outFile.toString())){
                continue;
            }
            writer.write("<PATH>" + ph.toString() + "</PATH>");
            writer.newLine();

            writer.write("<CODE>");
            writer.newLine();
            try (var reader = Files.newBufferedReader(ph, StandardCharsets.ISO_8859_1 )){
                String currentLine = null;
                while ((currentLine = reader.readLine()) != null){
                    writer.write(currentLine);
                    writer.newLine();
                }
                writer.write("</CODE>");
                writer.newLine();
            }
        }
        writer.close();
        System.out.println("File  " + outFile + " has been created");
        oDto.setResponseMessage("File  " + outFile + " has been created");
        oDto.setResponseCode("OK");
        return oDto;
    }

}

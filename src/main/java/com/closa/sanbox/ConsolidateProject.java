package com.closa.sanbox;

import com.closa.config.Properties;
import com.closa.services.CreateFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class ConsolidateProject {

    @Autowired
    Properties props;
    @Autowired
    CreateFile cf;

    public void ConsolidateTheProject () {
        System.out.println("Executing Building Consolidated Text file with SRC project...START");

        Path inPath = Path.of(props.getInproject());
        Path outFile = Path.of(props.getFile());
        try {
            cf.CreateTheFile(inPath, outFile);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Executing Building Consolidated Text file with SRC project...END");
    }


}

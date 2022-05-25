package com.closa.common;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Functions {
    private long getSize(Path p){
        try{
            return Files.size(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public long getPathSize(Path path) throws IOException{
        try
                (var s = Files.walk(path)){
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

    public final long convertBytesToMegas(long bytes){

        return bytes / Constants.BYTES2MEGAS;
    }




    public List<Path> listPathFiles(Path path) throws IOException{
        try
          (var s = Files.walk(path)){
            return s.parallel()
                    .filter(p -> p.isAbsolute())
                    .filter(p -> Files.isRegularFile(p))
                    .filter(p -> provideMIMEText(p))
                    .filter(p -> !p.getFileName().toString().startsWith("."))
                    .filter(p -> !p.toString().contains("node_modules"))
                    .filter(p -> !p.toString().contains("target"))
                    .filter(p -> !p.toString().contains(".git"))
                    .filter(p -> !p.toString().contains(".mvn"))
                    .filter(p -> !p.toString().contains(".idea"))
                    .map(f -> f.getParent().resolve(f.getFileName()))
                    .collect(Collectors.toList());
        }
    }

    public void deleteFiles(Path path ) throws IOException {
        try (var s = Files.walk(path)) {
            s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .forEach(p -> {
                        try {
                            Files.deleteIfExists(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        }

    }
    public void deleteEmptyFolders(Path path ) throws IOException,
            DirectoryNotEmptyException,
            NoSuchFileException {

        try (var n = Files.walk(path))  {
            n.sorted(Comparator.reverseOrder())
                    .filter(p -> Files.isDirectory(p))
                   //.forEach(Files::delete)
                      .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    )
            ;

        }
    }
    private boolean provideMIMEText(Path path){
        try {
            /**
             * for some reason, yml files return null
             */
            if (path.toString().endsWith(".yml") ||
                    path.toString().endsWith(".yaml") ||
                    path.toString().endsWith(".properties") ||
                    path.toString().endsWith(".js") ||
                    path.toString().endsWith(".json") ||
                    path.toString().endsWith(".ts")
            ) {
                return true;
            }

            if(Files.isRegularFile(path)){
                String mt = Files.probeContentType(path);
                return (mt != null) && (mt.contains("text/"));
            } else return true;


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}

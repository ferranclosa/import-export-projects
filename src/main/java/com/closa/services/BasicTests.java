package com.closa.services;

import org.springframework.stereotype.Component;

import java.io.IOException;

import java.nio.file.Path;


@Component

public class BasicTests {

        public void TestsOne () throws IOException {
            Path p =  Path.of("/land/extasis/undertow/system/beethoven/sonata_wing.jpeg");

            System.out.println("The Path name is = " +  p);
            System.out.println("The Root of the Path is = " +   p.getRoot());
            System.out.println("The file name within the Path is = " + p.getFileName());
            String str = p.getFileName().toString();
            if(str.endsWith(".jpeg")){
                System.out.println("Music file found! " + str);
            }
            System.out.println("The number of levels in the tree is n +  1 (0 based)  = " +  p.getNameCount());
            System.out.println("The name of whatever is in number 2 (0 based)  is " + p.getName(2));
            System.out.println("The name of whatever is in number 3 (0 based)  is " + p.getName(3));
            System.out.println("The name of whatever is in number 0 (0 based)  is " + p.getName(0));


            System.out.println("Using a loop...");
            for (int i = 0; i<p.getNameCount(); i++ ){
                System.out.println("Element " + i + " is " + p.getName(i));
            }
        }
}

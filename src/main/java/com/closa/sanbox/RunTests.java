package com.closa.sanbox;

import com.closa.NIO2Example;
import com.closa.services.BasicTests;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class RunTests {

    public void RunTheTests (){
        System.out.println("Executing different tests on NIO2 functionality...START");
        BasicTests tests = new BasicTests();
        try {
            tests.TestsOne();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Executing different tests on NIO2 functionality...END");


    }
}

package com.lushunde.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        System.out.println("args = " + Arrays.deepToString(args));
        SpringApplication.run(Application.class, args);
        System.out.println("Application.main");
    }

}

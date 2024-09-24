package com.team18.MBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"Controllers", "com/team18/MBC/core"})
public class Application {
    //jdv4@hi.is was here just now
    //vmp2@hi.is was here
    //oma9@hi.is was here
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

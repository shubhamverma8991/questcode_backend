package com.prepquest.prepquest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrepquestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrepquestApplication.class, args);
        System.out.print("Database is Up and Running ");
    }

}

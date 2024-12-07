package com.prepquest.prepquest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.prepquest.prepquest.model")
public class PrepquestApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrepquestApplication.class, args);
        System.out.print("Database is Up and Running ");
    }

}

package com.albert.godworld.arm.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResourceArm {

    public static void main(String[] args) {
        SpringApplication.run(ResourceArm.class,args);
    }
}

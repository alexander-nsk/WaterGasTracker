package com.igaming.watergastracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class WaterGasTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WaterGasTrackerApplication.class, args);
    }
}
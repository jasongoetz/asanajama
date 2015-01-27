package com.github.jasongoetz.asanajama;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Service {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(Service.class).showBanner(false).run(args);
    }
}

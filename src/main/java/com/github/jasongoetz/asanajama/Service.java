package com.github.jasongoetz.asanajama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Service {

    @Autowired
    private AsanaImporter asanaImporter;

    @Value("${asanaApiKey}")
    private String asanaApiKey;

    public static void main(String[] args) {
        new Service().start(args);
    }

    public void start(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(this.getClass()).showBanner(false).run(args);
    }

}

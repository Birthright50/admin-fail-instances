package com.bpcbt.marketplace.boot.cloud.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.expression.spel.SpelCompilerMode;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication extends SpringBootServletInitializer {

    static {
        System.setProperty("spring.expression.compiler.mode", SpelCompilerMode.MIXED.name());
    }

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DiscoveryServerApplication.class);
    }
}

package com.zuehlke.zrs.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MissingFunctionLevelAccessControlApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(MissingFunctionLevelAccessControlApplication.class, args);

        ctx.getBean(Bootstrapper.class).onStartup();
    }
}

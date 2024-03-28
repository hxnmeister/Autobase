package com.ua.project.Autobase;

import com.ua.project.Autobase.menu.MenuExecutor;
import com.ua.project.Autobase.service.AutobaseInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStarter {
    @Autowired
    MenuExecutor menuExecutor;

    @Autowired
    private AutobaseInitializer autobaseInitializer;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    @Bean
    public ApplicationRunner init() {
        LOGGER.info("Application is started!");

        return args -> {
          autobaseInitializer.autobaseInitialize();
          menuExecutor.startMenu();
        };
    }
}

package com.ua.project.Autobase;

import com.ua.project.Autobase.menu.MenuExecutor;
import com.ua.project.Autobase.utils.AutobaseInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class AppStarter {
    private final MenuExecutor menuExecutor;
    private final AutobaseInitializer autobaseInitializer;

    @Bean
    public ApplicationRunner init() {
        log.debug("Application is started!");

        return args -> {
          autobaseInitializer.autobaseInitialize();
          menuExecutor.startMenu();
        };
    }
}

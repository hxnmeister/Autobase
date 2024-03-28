package com.ua.project.Autobase.service;

import com.ua.project.Autobase.AppStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AutobaseInitializer {
    @Autowired
    private AutobaseDbInitializer autobaseDbInitializer;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    public void autobaseInitialize(){
        try {
            autobaseDbInitializer.dropTables();
            autobaseDbInitializer.createTables();

            autobaseDbInitializer.createRandomCargoTypes();
            autobaseDbInitializer.createRandomCars();
            autobaseDbInitializer.createRandomApplications();
            autobaseDbInitializer.createRandomDrivers();
            autobaseDbInitializer.createRandomDestinations();
            autobaseDbInitializer.createRandomRoutes();
            autobaseDbInitializer.createRandomCompletedRoutes();
        }
        catch (RuntimeException | IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}

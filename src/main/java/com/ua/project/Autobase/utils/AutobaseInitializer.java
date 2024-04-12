package com.ua.project.Autobase.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class AutobaseInitializer {
    private final AutobaseDbInitializer autobaseDbInitializer;

    public void autobaseInitialize(){
        try {
            autobaseDbInitializer.deleteAllRowsInDB();

            autobaseDbInitializer.createRandomCargoTypes();
            autobaseDbInitializer.createRandomCars();
            autobaseDbInitializer.createRandomApplications();
            autobaseDbInitializer.createRandomDrivers();
            autobaseDbInitializer.createRandomDestinations();
            autobaseDbInitializer.addDriversAccounts();
//            autobaseDbInitializer.createRandomRoutes();
//            autobaseDbInitializer.createRandomCompletedRoutes();
        }
        catch (RuntimeException | IOException e) {
            log.debug(e.getMessage());
        }
    }
}

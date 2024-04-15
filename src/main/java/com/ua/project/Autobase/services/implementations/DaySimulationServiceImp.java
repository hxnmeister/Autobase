package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.services.DaySimulationService;
import com.ua.project.Autobase.services.RouteService;
import com.ua.project.Autobase.services.RouteSimulationService;
import com.ua.project.Autobase.utils.AutobaseDbInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@Service
@RequiredArgsConstructor
public class DaySimulationServiceImp implements DaySimulationService {
    private final RouteService routeService;
    private final AutobaseDbInitializer autobaseDbInitializer;
    private final RouteSimulationService routeSimulationService;

    @Override
    public void startDaySimulation(long dayLengthInMillis) {
        routeService.setRoutes();

        while (true) {
            try {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    autobaseDbInitializer.createRandomApplications();
                    routeService.setRoutes();
                }

                if (routeSimulationService.startRouteSimulation().isEmpty()) {
                    throw new RuntimeException("There is no more routes!");
                }

                Thread.sleep(dayLengthInMillis);
            } catch (InterruptedException | RuntimeException e) {
                log.warn(e.getMessage());
            }
        }
    }
}

package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.services.DaySimulationService;
import com.ua.project.Autobase.services.RouteSimulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DaySimulationServiceImp implements DaySimulationService {
    private final RouteSimulationService routeSimulationService;

    @Override
    public void startDaySimulation() {
        while (true) {
            try {
                if (routeSimulationService.startRouteSimulation().isEmpty()) {
                    throw new RuntimeException("There is no more routes!");
                }

                Thread.sleep(5000);
            } catch (InterruptedException | RuntimeException e) {
                log.warn(e.getMessage());
            }
        }
    }
}

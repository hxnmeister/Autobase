package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.models.CompletedRoute;
import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.models.Route;
import com.ua.project.Autobase.services.CarService;
import com.ua.project.Autobase.services.CompletedRouteService;
import com.ua.project.Autobase.services.RouteService;
import com.ua.project.Autobase.services.RouteSimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class RouteSimulationServiceImp implements RouteSimulationService {
    private final CompletedRouteService completedRouteService;
    private final RouteService routeService;
    private final CarService carService;

    @Override
    public List<Route> startRouteSimulation() {
        List<Route> routes = routeService.findAll();

        if (routes.isEmpty()) {
            return new ArrayList<>();
        }

        List<Car> updatedCars = new ArrayList<>();

        for (Route route : routes) {
            if (route.getDistanceTraveled() >= route.getDestination().getDistance()) {
                final Driver driver = route.getDriver();
                final LocalDateTime endDate = LocalDateTime.now();

                driver.setEarnings(driver.getEarnings().add(route.getApplication().getCargoType().getCostPerKG().multiply(BigDecimal.valueOf(route.getDistanceTraveled()))));
                completedRouteService.save(CompletedRoute
                        .builder()
                        .car(route.getCar())
                        .driver(route.getDriver())
                        .destination(route.getDestination())
                        .application(route.getApplication())
                        .beginDate(Date.valueOf(calculateBeginDate(endDate, route.getDistanceTraveled(), ThreadLocalRandom.current().nextInt(40, 60)).toLocalDate()))
                        .endDate(Date.valueOf(endDate.toLocalDate()))
                        .build());

                routeService.delete(route);
                routes.remove(route);
            }
            else {
                updatedCars.add(carService.changeCarCondition(route.getCar()));

                if (route.getCar().getIsOnService() == 0) {
                    route.setDistanceTraveled(route.getDistanceTraveled() + ThreadLocalRandom.current().nextDouble(500, 1000));
                }
            }
        }

        if (!updatedCars.isEmpty()) {
            carService.saveMany(updatedCars);
        }
        if (!routes.isEmpty()) {
            routeService.saveMany(routes);
        }

        return routes;
    }

    private static LocalDateTime calculateBeginDate(LocalDateTime endDate, double distanceTravelled, int avgSpeed) {
        final double timeTravelled = distanceTravelled / avgSpeed;

        return endDate.minusHours((long) timeTravelled);
    }
}

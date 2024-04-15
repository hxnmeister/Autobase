package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.exceptions.CannotAddRouteException;
import com.ua.project.Autobase.models.*;
import com.ua.project.Autobase.repositories.RouteRepository;
import com.ua.project.Autobase.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class RouteServiceImp implements RouteService {
    private final RouteRepository routeRepository;
    private final DriverService driverService;
    private final CarService carService;
    private final ApplicationService applicationService;
    private final DestinationService destinationService;

    @Override
    public Route save(Route item) {
        return routeRepository.save(item);
    }

    @Override
    public Route update(Route item) {
        return routeRepository.save(item);
    }

    @Override
    public void delete(Route item) {
        routeRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        routeRepository.deleteAll();
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public List<Route> saveMany(List<Route> itemsList) {
        return routeRepository.saveAll(itemsList);
    }

    @Override
    public Route createRoute(Application application, List<Destination> destinations) {
        Route route = new Route();
        List<Car> cars = carService.getCarsByLoadCapacityIsGreaterThanAndNotOnRoute(application.getWeight());
        List<Driver> drivers = driverService.getDriversByDrivingExperienceIsGreaterThanAndNotOnRoute(application.getCargoType().getRequiredExperience());

        if (cars.isEmpty() || drivers.isEmpty()) {
            return Route.builder().build();
        }

        route.setDriver(drivers.stream().min(Comparator.comparingDouble(Driver::getDrivingExperience)).orElseThrow(CannotAddRouteException::new));
        route.setCar(cars.stream().min(Comparator.comparingDouble(Car::getLoadCapacity)).orElseThrow(CannotAddRouteException::new));

        if (route.getDriver() == null || route.getCar() == null) {
            throw new CannotAddRouteException(" Cannot add Application to Route: " + application);
        }

        route.setDistanceTraveled(0D);
        route.setDestination(destinations.get(ThreadLocalRandom.current().nextInt(destinations.size())));
        route.setApplication(application);
        return route;
    }

    @Override
    public boolean setRoutes() {
        List<Route> routes = routeRepository.findAll();
        List<Destination> destinations = destinationService.findAll();
        List<Application> applications = applicationService.getFreeApplications();

        for (Application application : applications) {
            Route route = this.createRoute(application, destinations);

            if(!routes.contains(route) && !route.equals(Route.builder().build())) {
                routes.add(routeRepository.save(route));
            }
        }

        return true;
    }

    @Override
    public Car findDriversCar(Long driverId) {
        return routeRepository.findDriversCar(driverId);
    }
}

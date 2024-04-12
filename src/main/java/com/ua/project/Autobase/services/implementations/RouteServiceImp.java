package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.exceptions.CannotAddRouteException;
import com.ua.project.Autobase.models.Application;
import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.repositories.RouteRepository;
import com.ua.project.Autobase.models.Route;
import com.ua.project.Autobase.services.ApplicationService;
import com.ua.project.Autobase.services.CarService;
import com.ua.project.Autobase.services.DriverService;
import com.ua.project.Autobase.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImp implements RouteService {
    private final RouteRepository routeRepository;
    private final DriverService driverService;
    private final CarService carService;
    private final ApplicationService applicationService;

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
    public Route createRoute(Application application) {
        Route route = new Route();
        List<Car> cars = carService.getCarsByLoadCapacityIsGreaterThanAndNotOnRoute(application.getWeight());
        List<Driver> drivers = driverService.getDriversByDrivingExperienceIsGreaterThanAndNotOnRoute(application.getCargoType().getRequiredExperience());

        route.setDriver(drivers.stream().min(Comparator.comparingDouble(Driver::getDrivingExperience)).orElseThrow(CannotAddRouteException::new));
        route.setCar(cars.stream().min(Comparator.comparingDouble(Car::getLoadCapacity)).orElseThrow(CannotAddRouteException::new));

        if (route.getDriver() == null || route.getCar() == null) {
            throw new CannotAddRouteException(" Cannot add Application to Route: " + application);
        }

        route.setDistanceTraveled(0D);
        route.setApplication(application);
        return route;
    }

    @Override
    public boolean setRoutes() {
        List<Route> routes = routeRepository.findAll();
        List<Application> notAssignedApplications = applicationService.getNotAssignedToRoutesApplications();

        for (Application application : notAssignedApplications) {
            try {
                Route route = this.createRoute(application);

                if(!routes.contains(route)) {
                    routes.add(routeRepository.save(route));
                }
            }
            catch (CannotAddRouteException e) {
                continue;
            }
        }

        return true;
    }

    @Override
    public Car findDriversCar(Long driverId) {
        return routeRepository.findDriversCar(driverId);
    }
}

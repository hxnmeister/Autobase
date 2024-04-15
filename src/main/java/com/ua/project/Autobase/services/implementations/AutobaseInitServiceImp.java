package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.*;
import com.ua.project.Autobase.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutobaseInitServiceImp implements AutobaseInitService {
    private final CarService carService;
    private final UserService userService;
    private final RoleService roleService;
    private final RouteService routeService;
    private final DriverService driverService;
    private final UserRoleService userRoleService;
    private final CargoTypeService cargoTypeService;
    private final ApplicationService applicationService;
    private final DestinationService destinationService;
    private final CompletedRouteService completedRouteService;

    @Override
    public void deleteAllRowsInDB() {
        carService.deleteAll();
        userService.deleteAll();
        roleService.deleteAll();
        routeService.deleteAll();
        driverService.deleteAll();
        userRoleService.deleteAll();
        cargoTypeService.deleteAll();
        applicationService.deleteAll();
        destinationService.deleteAll();
        completedRouteService.deleteAll();
    }

    @Override
    public List<Car> findAllCars() {
        return carService.findAll();
    }

    @Override
    public List<Route> findAllRoutes() {
        return routeService.findAll();
    }

    @Override
    public List<Driver> findAllDrivers() {
        return driverService.findAll();
    }

    @Override
    public List<CargoType> findAllCargoTypes() {
        return cargoTypeService.findAll();
    }

    @Override
    public List<Application> findAllApplications() {
        return applicationService.findAll();
    }

    @Override
    public List<Destination> findAllDestinations() {
        return destinationService.findAll();
    }

    @Override
    public List<CompletedRoute> findAllCompletedRoutes() {
        return completedRouteService.findAll();
    }

    @Override
    public void saveCars(List<Car> cars) {
        carService.saveMany(cars);
    }

    @Override
    public void saveRoutes(List<Route> routes) {
        routeService.saveMany(routes);
    }

    @Override
    public void saveDrivers(List<Driver> drivers) {
        driverService.saveMany(drivers);
    }

    @Override
    public void saveCargoTypes(List<CargoType> cargoTypes) {
        cargoTypeService.saveMany(cargoTypes);
    }

    @Override
    public void saveApplications(List<Application> applications) {
        applicationService.saveMany(applications);
    }

    @Override
    public void saveDestinations(List<Destination> destinations) {
        destinationService.saveMany(destinations);
    }

    @Override
    public void saveCompletedRoutes(List<CompletedRoute> completedRoutes) {
        completedRouteService.saveMany(completedRoutes);
    }

    @Override
    public List<UserRole> saveUsersAndApplyRole(List<User> users, String role) {
        return userRoleService.saveUsersAndApplyRole(users, role);
    }

    @Override
    public User saveUserAndApplyRole(User user, String role) {
        return userRoleService.saveUserAndApplyRole(user, role);
    }
}

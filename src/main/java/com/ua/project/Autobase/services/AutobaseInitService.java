package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.*;

import java.util.List;

public interface AutobaseInitService {
    void deleteAllRowsInDB();
    void dropAllTablesInDB();
    void createTablesInDB();

    List<Car> findAllCars();
    List<Route> findAllRoutes();
    List<Driver> findAllDrivers();
    List<CargoType> findAllCargoTypes();
    List<Application> findAllApplications();
    List<Destination> findAllDestinations();
    List<CompletedRoute> findAllCompletedRoutes();

    void saveCars(List<Car> cars);
    void saveRoutes(List<Route> routes);
    void saveDrivers(List<Driver> drivers);
    void saveCargoTypes(List<CargoType> cargoTypes);
    void saveApplications(List<Application> applications);
    void saveDestinations(List<Destination> destinations);
    void saveCompletedRoutes(List<CompletedRoute> completedRoutes);
    void saveUsersAndApplyDriverRole(List<User> users);
}

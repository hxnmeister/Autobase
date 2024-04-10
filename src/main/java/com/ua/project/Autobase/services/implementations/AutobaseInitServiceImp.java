package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.ApplicationRepository;
import com.ua.project.Autobase.repositories.CarRepository;
import com.ua.project.Autobase.repositories.CargoTypeRepository;
import com.ua.project.Autobase.repositories.CompletedRouteRepository;
import com.ua.project.Autobase.repositories.DestinationRepository;
import com.ua.project.Autobase.repositories.DriverRepository;
import com.ua.project.Autobase.repositories.RouteRepository;
import com.ua.project.Autobase.models.*;
import com.ua.project.Autobase.services.AutobaseInitService;
import com.ua.project.Autobase.services.TxtFileReader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutobaseInitServiceImp implements AutobaseInitService {
    @Value("${data.dropSqlTables}")
    private String pathToDropTables;

    @Value("${data.createSqlTables}")
    private String pathToCreateTables;

    private final JdbcTemplate jdbcTemplate;
    private final TxtFileReader txtFileReader;
    private final CarRepository carRepository;
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final CargoTypeRepository cargoTypeRepository;
    private final ApplicationRepository applicationRepository;
    private final DestinationRepository destinationRepository;
    private final CompletedRouteRepository completedRouteRepository;

    @Override
    public void deleteAllRowsInDB() {
        carRepository.deleteAll();
        routeRepository.deleteAll();
        driverRepository.deleteAll();
        cargoTypeRepository.deleteAll();
        applicationRepository.deleteAll();
        destinationRepository.deleteAll();
        completedRouteRepository.deleteAll();
    }

    @Override
    public void dropAllTablesInDB() {
        executeCreateOrDropQuery(pathToDropTables);
    }

    @Override
    public void createTablesInDB() {
        executeCreateOrDropQuery(pathToCreateTables);
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Route> findAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public List<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public List<CargoType> findAllCargoTypes() {
        return cargoTypeRepository.findAll();
    }

    @Override
    public List<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Destination> findAllDestinations() {
        return destinationRepository.findAll();
    }

    @Override
    public List<CompletedRoute> findAllCompletedRoutes() {
        return completedRouteRepository.findAll();
    }

    @Override
    public void saveCars(List<Car> cars) {
        carRepository.saveAll(cars);
    }

    @Override
    public void saveRoutes(List<Route> routes) {
        routeRepository.saveAll(routes);
    }

    @Override
    public void saveDrivers(List<Driver> drivers) {
        driverRepository.saveAll(drivers);
    }

    @Override
    public void saveCargoTypes(List<CargoType> cargoTypes) {
        cargoTypeRepository.saveAll(cargoTypes);
    }

    @Override
    public void saveApplications(List<Application> applications) {
        applicationRepository.saveAll(applications);
    }

    @Override
    public void saveDestinations(List<Destination> destinations) {
        destinationRepository.saveAll(destinations);
    }

    @Override
    public void saveCompletedRoutes(List<CompletedRoute> completedRoutes) {
        completedRouteRepository.saveAll(completedRoutes);
    }

    private void executeCreateOrDropQuery(String path) {
        txtFileReader.setFileName(path);

        try {
            StringBuilder builder = new StringBuilder();
            List<String> fileData = txtFileReader.readFile();

            for (String line : fileData) {
                builder.append(line).append("\n");
            }

            jdbcTemplate.update(builder.toString());
        }
        catch (IOException | DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
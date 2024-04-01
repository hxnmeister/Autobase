package com.ua.project.Autobase.utils;

import com.ua.project.Autobase.models.*;
import com.ua.project.Autobase.services.TxtFileReader;
import com.ua.project.Autobase.services.autobase_init_service.AutobaseInitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class AutobaseDbInitializer {
    @Value("${data.car.manufacturers}")
    private String pathToCarManufacturers;

    @Value("${data.first_names}")
    private String pathToFirstNames;

    @Value("${data.last_names}")
    private String pathToLastNames;

    private final TxtFileReader txtFileReader;
    private final JdbcTemplate jdbcTemplate;
    private final AutobaseInitService autobaseInitService;
    private static final Random RANDOM = new Random();

    public void deleteAllRowsInDB() {
        autobaseInitService.deleteAllRowsInDB();
        log.debug("All data from DB erased!");
    }

    public void createRandomCargoTypes() {
        final int COUNT_OF_ITEMS_IN_LIST = 4;
        final int MAX_RANDOM_NUMBER = 20;
        final int MONEY_COEFFICIENT = 1000;
        List<CargoType> cargoTypes = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            cargoTypes.add(CargoType
                    .builder()
                    .title("CargoTypeTitle" + (i + RANDOM.nextInt(MAX_RANDOM_NUMBER)))
                    .costPerKG(BigDecimal.valueOf(RANDOM.nextDouble() * MONEY_COEFFICIENT))
                    .requiredExperience(getRandomDoubleValueWithCoefficient(100.0))
                    .build());
        }

        autobaseInitService.saveCargoTypes(cargoTypes);
    }

    public void createRandomCars() throws IOException {
        txtFileReader.setFileName(pathToCarManufacturers);
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final int MIN_ACCEPTABLE_CONDITION = 50;
        final double MIN_ACCEPTABLE_WEIGHT = 500.0;
        List<Car> cars = new ArrayList<>();
        List<String> manufacturers = txtFileReader.readFile();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            cars.add(Car
                    .builder()
                    .model("Model" + (i + 1))
                    .condition(Math.max(RANDOM.nextInt(101), MIN_ACCEPTABLE_CONDITION))
                    .isOnService(0)
                    .manufacturer(manufacturers.get(RANDOM.nextInt(manufacturers.size())))
                    .loadCapacity(Math.max(getRandomDoubleValueWithCoefficient(1000.0), MIN_ACCEPTABLE_WEIGHT))
                    .build());
        }

        autobaseInitService.saveCars(cars);
    }

    public void createRandomApplications() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final double MIN_ACCEPTABLE_WEIGHT = 500.0;
        List<Application> applications = new ArrayList<>();
        List<CargoType> cargoTypes = autobaseInitService.findAllCargoTypes();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            applications.add(Application
                    .builder()
                    .weight(Math.max(getRandomDoubleValueWithCoefficient(1000.0), MIN_ACCEPTABLE_WEIGHT))
                    .cargoType(cargoTypes.get(RANDOM.nextInt(cargoTypes.size())))
                    .build());
        }

        autobaseInitService.saveApplications(applications);
    }

    public void createRandomDrivers() throws IOException {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final double MIN_ACCEPTABLE_EXPERIENCE = 3;
        final BigDecimal MIN_ACCEPTABLE_EARNINGS = BigDecimal.valueOf(50.0);
        List<Driver> drivers = new ArrayList<>();
        txtFileReader.setFileName(pathToFirstNames);
        List<String> firstNames = txtFileReader.readFile();
        txtFileReader.setFileName(pathToLastNames);
        List<String> lastNames = txtFileReader.readFile();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            drivers.add(Driver
                    .builder()
                    .firstName(firstNames.get(RANDOM.nextInt(firstNames.size())))
                    .lastName(lastNames.get(RANDOM.nextInt(lastNames.size())))
                    .earnings(MIN_ACCEPTABLE_EARNINGS.max(BigDecimal.valueOf(getRandomDoubleValueWithCoefficient(1000.0))))
                    .drivingExperience(Math.max(MIN_ACCEPTABLE_EXPERIENCE, getRandomDoubleValueWithCoefficient(100.0)))
                    .build());
        }

        autobaseInitService.saveDrivers(drivers);
    }

    public void createRandomDestinations() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final double MIN_ACCEPTABLE_DISTANCE = 20.0;
        List<Destination> destinations = new ArrayList<>();

        for (int i = 1; i <= COUNT_OF_ITEMS_IN_LIST; i++) {
            destinations.add(Destination
                    .builder()
                    .distance(Math.max(getRandomDoubleValueWithCoefficient(1000.0), MIN_ACCEPTABLE_DISTANCE))
                    .country("Country" + i)
                    .city("City" + i)
                    .build());
        }

        autobaseInitService.saveDestinations(destinations);
    }

    public void createRandomRoutes() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        List<Application> applications = autobaseInitService.findAllApplications();
        List<Driver> drivers = autobaseInitService.findAllDrivers();
        List<Car> cars = autobaseInitService.findAllCars();
        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            routes.add(Route
                    .builder()
                    .application(applications.get(RANDOM.nextInt(applications.size())))
                    .driver(drivers.get(RANDOM.nextInt(drivers.size())))
                    .car(cars.get(RANDOM.nextInt(cars.size())))
                    .build());
        }

        autobaseInitService.saveRoutes(routes);
    }

    public void createRandomCompletedRoutes() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        Calendar calendar = new GregorianCalendar();
        List<Route> routes = autobaseInitService.findAllRoutes();
        List<CompletedRoute> completedRoutes = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            java.sql.Date beginDate = getRandomDate(calendar);
            java.sql.Date endDate = new java.sql.Date(beginDate.getTime());
            endDate.setTime(endDate.getTime() + (Math.max(RANDOM.nextInt(30), 10) * 24L * 3600L * 1000L));

            completedRoutes.add(CompletedRoute
                    .builder()
                    .beginDate(beginDate)
                    .endDate(endDate)
                    .route(routes.get(RANDOM.nextInt(routes.size())))
                    .build());
        }

        autobaseInitService.saveCompletedRoutes(completedRoutes);
    }

    private java.sql.Date getRandomDate(Calendar calendar) {
        final int DAYS_IN_YEAR = 365;
        Calendar randomDate = (Calendar) calendar.clone();

        randomDate.add(Calendar.DAY_OF_YEAR, -AutobaseDbInitializer.RANDOM.nextInt(DAYS_IN_YEAR));

        return new java.sql.Date(randomDate.getTimeInMillis());
    }

    private double getRandomDoubleValueWithCoefficient(double coefficient) {
        return Math.round(AutobaseDbInitializer.RANDOM.nextDouble() * coefficient * 10.0) / 10.0;
    }
}

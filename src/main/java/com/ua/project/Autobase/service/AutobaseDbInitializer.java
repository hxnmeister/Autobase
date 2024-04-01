package com.ua.project.Autobase.service;

import com.ua.project.Autobase.dao.cargo_typeDAO.CargoTypeDao;
import com.ua.project.Autobase.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AutobaseDbInitializer {
    @Value("${data.car.manufacturers}")
    private String pathToCarManufacturers;

    @Value("${data.first_names}")
    private String pathToFirstNames;

    @Value("${data.last_names}")
    private String pathToLastNames;

    @Value("${data.createSqlTables}")
    private String pathToCreateTables;

    @Value("${data.dropSqlTables}")
    private String pathToDropTables;

    @Autowired
    private TxtFileReader txtFileReader;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final CarDao carDao;
    private final RouteDao routeDao;
    private final DriverDao driverDao;
    private final CargoTypeDao cargoTypeDao;
    private final ApplicationDao applicationDao;
    private final DestinationDao destinationDao;
    private final CompletedRouteDao completedRouteDao;
    private static final Random RANDOM = new Random();

    public void dropTables() {
        executeCreateOrDropQuery(pathToDropTables);
    }

    public void createTables() {
        executeCreateOrDropQuery(pathToCreateTables);
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
                    .requiredExperience(getRandomDoubleValueWithCoefficient(RANDOM, 100.0))
                    .build());
        }

        cargoTypeDao.saveMany(cargoTypes);
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
                    .isOnService(false)
                    .manufacturer(manufacturers.get(RANDOM.nextInt(manufacturers.size())))
                    .loadCapacity(Math.max(getRandomDoubleValueWithCoefficient(RANDOM, 1000.0), MIN_ACCEPTABLE_WEIGHT))
                    .build());
        }

        carDao.saveMany(cars);
    }

    public void createRandomApplications() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final double MIN_ACCEPTABLE_WEIGHT = 500.0;
        List<Application> applications = new ArrayList<>();
        List<CargoType> cargoTypes = cargoTypeDao.findAll();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            applications.add(Application
                    .builder()
                    .weight(Math.max(getRandomDoubleValueWithCoefficient(RANDOM, 1000.0), MIN_ACCEPTABLE_WEIGHT))
                    .cargoTypeId(cargoTypes.get(RANDOM.nextInt(cargoTypes.size())).getId())
                    .build());
        }

        applicationDao.saveMany(applications);
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
                    .earnings(MIN_ACCEPTABLE_EARNINGS.max(BigDecimal.valueOf(getRandomDoubleValueWithCoefficient(RANDOM, 1000.0))))
                    .drivingExperience(Math.max(MIN_ACCEPTABLE_EXPERIENCE, getRandomDoubleValueWithCoefficient(RANDOM, 100.0)))
                    .build());
        }

        driverDao.saveMany(drivers);
    }

    public void createRandomDestinations() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final double MIN_ACCEPTABLE_DISTANCE = 20.0;
        List<Destination> destinations = new ArrayList<>();

        for (int i = 1; i <= COUNT_OF_ITEMS_IN_LIST; i++) {
            destinations.add(Destination
                    .builder()
                    .distance(Math.max(getRandomDoubleValueWithCoefficient(RANDOM, 1000.0), MIN_ACCEPTABLE_DISTANCE))
                    .country("Country" + i)
                    .city("City" + i)
                    .build());
        }

        destinationDao.saveMany(destinations);
    }

    public void createRandomRoutes() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        List<Application> applications = applicationDao.findAll();
        List<Driver> drivers = driverDao.findAll();
        List<Car> cars = carDao.findAll();
        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            routes.add(Route
                    .builder()
                    .applicationId(applications.get(RANDOM.nextInt(applications.size())).getId())
                    .driverId(drivers.get(RANDOM.nextInt(drivers.size())).getId())
                    .carId(cars.get(RANDOM.nextInt(cars.size())).getId())
                    .build());
        }

        routeDao.saveMany(routes);
    }

    public void createRandomCompletedRoutes() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        Calendar calendar = new GregorianCalendar();
        List<Route> routes = routeDao.findAll();
        List<CompletedRoute> completedRoutes = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            java.sql.Date beginDate = getRandomDate(RANDOM, calendar);
            java.sql.Date endDate = new java.sql.Date(beginDate.getTime());
            endDate.setTime(endDate.getTime() + (Math.max(RANDOM.nextInt(30), 10) * 24L * 3600L * 1000L));

            completedRoutes.add(CompletedRoute
                    .builder()
                    .beginDate(beginDate)
                    .endDate(endDate)
                    .routeId(routes.get(RANDOM.nextInt(routes.size())).getId())
                    .build());
        }

        completedRouteDao.saveMany(completedRoutes);
    }

    private java.sql.Date getRandomDate(Random random, Calendar calendar) {
        final int DAYS_IN_YEAR = 365;
        Calendar randomDate = (Calendar) calendar.clone();

        randomDate.add(Calendar.DAY_OF_YEAR, -random.nextInt(DAYS_IN_YEAR));

        return new java.sql.Date(randomDate.getTimeInMillis());
    }

    private double getRandomDoubleValueWithCoefficient(Random random, double coefficient) {
        return Math.round(random.nextDouble() * coefficient * 10.0) / 10.0;
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

package com.ua.project.Autobase.service;

import com.ua.project.Autobase.dao.applicationDAO.ApplicationDao;
import com.ua.project.Autobase.dao.carDAO.CarDao;
import com.ua.project.Autobase.dao.cargo_typeDAO.CargoTypeDao;
import com.ua.project.Autobase.dao.completed_routeDAO.CompletedRouteDao;
import com.ua.project.Autobase.dao.destinationDAO.DestinationDao;
import com.ua.project.Autobase.dao.driverDAO.DriverDao;
import com.ua.project.Autobase.dao.routeDAO.RouteDao;
import com.ua.project.Autobase.model.Application;
import com.ua.project.Autobase.model.CargoType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AutobaseDbInitializer {
    @Value("${data.car.manufacturers}")
    private String pathToCarManufacturers;

    @Value("${data.createSqlTables}")
    private String pathToCreateTables;

    @Value("${data.dropSqlTables}")
    private String pathToDropTables;

    @Autowired
    private TxtFileReader txtFileReader;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CarDao carDao;
    private RouteDao routeDao;
    private DriverDao driverDao;
    private CargoTypeDao cargoTypeDao;
    private ApplicationDao applicationDao;
    private DestinationDao destinationDao;
    private CompletedRouteDao completedRouteDao;
    private static final Random RANDOM = new Random();

    public AutobaseDbInitializer(CarDao carDao,
                                 RouteDao routeDao,
                                 DriverDao driverDao,
                                 CargoTypeDao cargoTypeDao,
                                 ApplicationDao applicationDao,
                                 DestinationDao destinationDao,
                                 CompletedRouteDao completedRouteDao) {
        this.carDao = carDao;
        this.routeDao = routeDao;
        this.driverDao = driverDao;
        this.cargoTypeDao = cargoTypeDao;
        this.applicationDao = applicationDao;
        this.destinationDao = destinationDao;
        this.completedRouteDao = completedRouteDao;
    }

    public void dropTables() {
        executeCreateOrDropQuery(pathToDropTables);
    }

    public void createTables() {
        executeCreateOrDropQuery(pathToCreateTables);
    }

    public void createRandomCargoTypes() {
        final int COUNT_OF_ITEMS_IN_LIST = 6;
        final int MAX_RANDOM_NUMBER = 20;
        final int MONEY_COEFFICIENT = 1000;
        List<CargoType> cargoTypes = new ArrayList<>();

        for (int i = 0; i < COUNT_OF_ITEMS_IN_LIST; i++) {
            cargoTypes.add(CargoType
                    .builder()
                    .title("CargoTypeTitle" + (i + RANDOM.nextInt(MAX_RANDOM_NUMBER)))
                    .costPerKG(BigDecimal.valueOf(RANDOM.nextDouble() * MONEY_COEFFICIENT))
                    .requiredExperience(Math.round(RANDOM.nextDouble() * 100 * 10.0) / 10.0)
                    .build());
        }

        cargoTypeDao.saveMany(cargoTypes);
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

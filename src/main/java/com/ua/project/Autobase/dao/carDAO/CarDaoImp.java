package com.ua.project.Autobase.dao.carDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Car> carRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_CARS = """
        SELECT *
        FROM cars
    """;
    private static final String INSERT_CAR = """
        INSERT INTO cars(model, condition, is_on_service, manufacturer, load_capacity)
        VALUES (?, ?, ?, ?, ?)
    """;
    private static final String UPDATE_CAR = """
        UPDATE cars
        SET model=?, condition=?, is_on_service=?, manufacturer=?, load_capacity=?
        WHERE id=?
    """;
    private static final String DELETE_CAR = """
        DELETE FROM cars
        WHERE id=?
    """;
    private static final String DELETE_CARS = """
        DELETE FROM cars
    """;

    @Override
    public int save(Car item) {
        try {
            return jdbcTemplate.update(INSERT_CAR,
                    item.getModel(),
                    item.getCondition(),
                    item.isOnService(),
                    item.getManufacturer(),
                    item.getLoadCapacity());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<Car> items) {
        try {
            return jdbcTemplate.batchUpdate(INSERT_CAR, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, items.get(i).getModel());
                    ps.setInt(2, items.get(i).getCondition());
                    ps.setBoolean(3, items.get(i).isOnService());
                    ps.setString(4, items.get(i).getManufacturer());
                    ps.setDouble(5, items.get(i).getLoadCapacity());
                }

                @Override
                public int getBatchSize() {
                    return items.size();
                }
            });
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new int[0];
    }

    @Override
    public int update(Car item) {
        try {
            return jdbcTemplate.update(UPDATE_CAR,
                    item.getModel(),
                    item.getCondition(),
                    item.isOnService(),
                    item.getManufacturer(),
                    item.getLoadCapacity(),
                    item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public List<Car> findAll() {
        try {
            return jdbcTemplate.query(SELECT_CARS, carRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(Car item) {
        try {
            return jdbcTemplate.update(DELETE_CAR, item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_CARS);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

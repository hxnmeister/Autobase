package com.ua.project.Autobase.dao.driverDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.Car;
import com.ua.project.Autobase.model.Driver;
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
public class DriverDaoImp implements DriverDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Driver> driverRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_DRIVERS = """
        SELECT *
        FROM drivers
    """;
    private static final String INSERT_DRIVER = """
        INSERT INTO drivers(first_name, last_name, earnings, driving_experience)
        VALUES (?, ?, ?, ?)
    """;
    private static final String UPDATE_DRIVER = """
        UPDATE drivers
        SET first_name=?, last_name=?, earnings=?, driving_experience=?
        WHERE id=?
    """;
    private static final String DELETE_DRIVER = """
        DELETE FROM drivers
        WHERE id=?
    """;
    private static final String DELETE_DRIVERS = """
        DELETE FROM drivers
    """;

    @Override
    public int save(Driver item) {
        try {
            return jdbcTemplate.update(INSERT_DRIVER,
                    item.getFirstName(),
                    item.getLastName(),
                    item.getEarnings(),
                    item.getDrivingExperience());

        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<Driver> items) {
        try {
            return jdbcTemplate.batchUpdate(UPDATE_DRIVER, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, items.get(i).getFirstName());
                    ps.setString(2, items.get(i).getLastName());
                    ps.setBigDecimal(3, items.get(i).getEarnings());
                    ps.setDouble(4, items.get(i).getDrivingExperience());
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
    public int update(Driver item) {
        try {
            return jdbcTemplate.update(UPDATE_DRIVER,
                    item.getFirstName(),
                    item.getLastName(),
                    item.getEarnings(),
                    item.getDrivingExperience(),
                    item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public List<Driver> findAll() {
        try {
            return jdbcTemplate.query(SELECT_DRIVERS, driverRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(Driver item) {
        try {
            return jdbcTemplate.update(DELETE_DRIVER, item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_DRIVERS);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

package com.ua.project.Autobase.dao.carDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Car> carRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_ALL = """
        SELECT *
        FROM applications
    """;
    private static final String INSERT_APPLICATION = """
        INSERT INTO applications(weight, cargo_type_id)
        VALUES (?, ?)
    """;
    private static final String UPDATE_APPLICATION = """
        UPDATE application
        SET weight=?
        WHERE id=?
    """;
    private static final String DELETE_APPLICATION = """
        DELETE FROM application
        WHERE id=?
    """;
    private static final String DELETE_APPLICATIONS = """
        DELETE FROM application
    """;

    @Override
    public int save(Car item) {
        return 0;
    }

    @Override
    public int[] saveMany(List<Car> items) {
        return new int[0];
    }

    @Override
    public int update(Car item) {
        return 0;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public int delete(Car item) {
        return 0;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}

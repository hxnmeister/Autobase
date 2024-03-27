package com.ua.project.Autobase.dao.cargo_typeDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.CargoType;
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
public class CargoTypeDaoImp implements CargoTypeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<CargoType> cargoTypeRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_CARGO_TYPES = """
        SELECT *
        FROM cargo_types
    """;
    private static final String INSERT_CARGO_TYPE = """
        INSERT INTO cargo_types(title, cost_per_kg, required_experience)
        VALUES (?, ?, ?)
    """;
    private static final String UPDATE_CARGO_TYPE = """
        UPDATE cargo_types
        SET title=?, cost_per_kg=?, required_experience=?
        WHERE id=?
    """;
    private static final String DELETE_CARGO_TYPE = """
        DELETE FROM cargo_types
        WHERE id=?
    """;
    private static final String DELETE_CARGO_TYPES = """
        DELETE FROM cargo_types
    """;

    @Override
    public int save(CargoType item) {
        try {
            return jdbcTemplate.update(INSERT_CARGO_TYPE, item.getTitle(), item.getCostPerKG(), item.getRequiredExperience());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<CargoType> items) {
        try {
            return jdbcTemplate.batchUpdate(INSERT_CARGO_TYPE, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, items.get(i).getTitle());
                    ps.setBigDecimal(2, items.get(i).getCostPerKG());
                    ps.setDouble(3, items.get(i).getRequiredExperience());
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
    public int update(CargoType item) {
        try {
            return jdbcTemplate.update(UPDATE_CARGO_TYPE, item.getTitle(), item.getCostPerKG(), item.getRequiredExperience());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public List<CargoType> findAll() {
        try {
            return jdbcTemplate.query(SELECT_CARGO_TYPES, cargoTypeRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(CargoType item) {
        try {
            return jdbcTemplate.update(DELETE_CARGO_TYPE, item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_CARGO_TYPES);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

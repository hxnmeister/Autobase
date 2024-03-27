package com.ua.project.Autobase.dao.applicationDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.Application;
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
public class ApplicationDaoImp implements ApplicationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Application> applicationRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_APPLICATIONS = """
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
    public int save(Application item) {
        try {
            return jdbcTemplate.update(INSERT_APPLICATION, item.getWeight(), item.getCargoTypeId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<Application> items) {
        try {
            return jdbcTemplate.batchUpdate(INSERT_APPLICATION, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setDouble(1, items.get(i).getWeight());
                    ps.setInt(2, items.get(i).getCargoTypeId());
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
    public int update(Application item) {
        try {
            return jdbcTemplate.update(UPDATE_APPLICATION, item.getWeight(), item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public List<Application> findAll() {
        try {
            return jdbcTemplate.query(SELECT_APPLICATIONS, applicationRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(Application item) {
        try {
            return jdbcTemplate.update(DELETE_APPLICATION, item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_APPLICATIONS);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

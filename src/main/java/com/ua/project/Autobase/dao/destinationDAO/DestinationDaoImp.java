package com.ua.project.Autobase.dao.destinationDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.Destination;
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
public class DestinationDaoImp implements DestinationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Destination> destinationRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_DESTINATIONS = """
        SELECT *
        FROM destinations
    """;
    private static final String INSERT_DESTINATION = """
        INSERT INTO destinations(distance, country, city)
        VALUES (?, ?, ?)
    """;
        private static final String UPDATE_DESTINATION = """
        UPDATE destinations
        SET distance=?, country=?, city=?
        WHERE id=?
    """;
    private static final String DELETE_DESTINATION = """
        DELETE FROM destinations
        WHERE id=?
    """;
    private static final String DELETE_DESTINATIONS = """
        DELETE FROM destinations
    """;

    @Override
    public int save(Destination item) {
        try {
            return jdbcTemplate.update(INSERT_DESTINATION, item.getDistance(), item.getCountry(), item.getCity());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<Destination> items) {
        try {
            return jdbcTemplate.batchUpdate(INSERT_DESTINATION, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setDouble(1, items.get(i).getDistance());
                    ps.setString(2, items.get(i).getCountry());
                    ps.setString(3, items.get(i).getCity());
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
    public int update(Destination item) {
        try {
            return jdbcTemplate.update(UPDATE_DESTINATION, item.getDistance(), item.getCountry(), item.getCity());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public List<Destination> findAll() {
        try {
            return jdbcTemplate.query(SELECT_DESTINATIONS, destinationRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(Destination item) {
        try {
            return jdbcTemplate.update(DELETE_DESTINATION, item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_DESTINATIONS);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

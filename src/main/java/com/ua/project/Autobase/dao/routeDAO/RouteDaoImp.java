package com.ua.project.Autobase.dao.routeDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.Driver;
import com.ua.project.Autobase.model.Route;
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
public class RouteDaoImp implements RouteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Route> routeRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_ROUTES = """
        SELECT *
        FROM routes
    """;
    private static final String INSERT_ROUTE = """
        INSERT INTO routes(application_id, driver_id, car_id)
        VALUES (?, ?, ?)
    """;
    private static final String DELETE_ROUTE = """
        DELETE FROM routes
        WHERE id=?
    """;
    private static final String DELETE_ROUTES = """
        DELETE FROM routes
    """;

    @Override
    public int save(Route item) {
        try {
            return jdbcTemplate.update(INSERT_ROUTE,
                    item.getApplicationId(),
                    item.getDriverId(),
                    item.getCarId());

        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<Route> routes) {
        try {
            return jdbcTemplate.batchUpdate(INSERT_ROUTE, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1, routes.get(i).getApplicationId());
                    ps.setLong(2, routes.get(i).getDriverId());
                    ps.setLong(3, routes.get(i).getCarId());
                }

                @Override
                public int getBatchSize() {
                    return routes.size();
                }
            });
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new int[0];
    }

    @Override
    public List<Route> findAll() {
        try {
            return jdbcTemplate.query(SELECT_ROUTES, routeRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(Route route) {
        try {
            return jdbcTemplate.update(DELETE_ROUTE, route.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_ROUTES);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

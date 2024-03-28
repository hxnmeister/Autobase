package com.ua.project.Autobase.dao.completed_routeDAO;

import com.ua.project.Autobase.AppStarter;
import com.ua.project.Autobase.model.CompletedRoute;
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
public class CompletedRouteDaoImp implements CompletedRouteDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<CompletedRoute> completedRouteRowMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);
    private static final String SELECT_COMPLETED_ROUTES = """
        SELECT *
        FROM completed_routes
    """;
    private static final String INSERT_COMPLETED_ROUTE = """
        INSERT INTO completed_routes(begin_date, end_date, route_id)
        VALUES (?, ?, ?)
    """;
    private static final String UPDATE_COMPLETED_ROUTE = """
        UPDATE completed_routes
        SET begin_date=?, end_date=?
        WHERE id=?
    """;
    private static final String DELETE_COMPLETED_ROUTE = """
        DELETE FROM completed_routes
        WHERE id=?
    """;
    private static final String DELETE_COMPLETED_ROUTES = """
        DELETE FROM completed_routes
    """;

    @Override
    public int save(CompletedRoute item) {
        try {
            return jdbcTemplate.update(INSERT_COMPLETED_ROUTE, item.getBeginDate(), item.getEndDate(), item.getRouteId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int[] saveMany(List<CompletedRoute> items) {
        try {
            return jdbcTemplate.batchUpdate(INSERT_COMPLETED_ROUTE, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setDate(1, items.get(i).getBeginDate());
                    ps.setDate(2, items.get(i).getEndDate());
                    ps.setLong(3, items.get(i).getRouteId());
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
    public int update(CompletedRoute item) {
        try {
            return jdbcTemplate.update(UPDATE_COMPLETED_ROUTE, item.getBeginDate(), item.getEndDate(), item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public List<CompletedRoute> findAll() {
        try {
            return jdbcTemplate.query(SELECT_COMPLETED_ROUTES, completedRouteRowMapper);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public int delete(CompletedRoute item) {
        try {
            return jdbcTemplate.update(DELETE_COMPLETED_ROUTE, item.getId());
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }

    @Override
    public int deleteAll() {
        try {
            return jdbcTemplate.update(DELETE_COMPLETED_ROUTES);
        }
        catch (DataAccessException e) {
            LOGGER.info(e.getMessage());
        }

        return -1;
    }
}

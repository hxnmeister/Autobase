package com.ua.project.Autobase.dao.routeDAO;

import com.ua.project.Autobase.model.Route;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RouteRowMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Route
                .builder()
                .id(rs.getLong("id"))
                .applicationId(rs.getInt("application_id"))
                .driverId(rs.getInt("driver_id"))
                .carId(rs.getInt("car_id"))
                .build();
    }
}

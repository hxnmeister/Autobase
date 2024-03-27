package com.ua.project.Autobase.dao.driverDAO;

import com.ua.project.Autobase.model.Driver;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DriverRowMapper implements RowMapper<Driver> {
    @Override
    public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Driver
                .builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .earnings(rs.getBigDecimal("earnings"))
                .drivingExperience(rs.getDouble("driving_experience"))
                .build();
    }
}

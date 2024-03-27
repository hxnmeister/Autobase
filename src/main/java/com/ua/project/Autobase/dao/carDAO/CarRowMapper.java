package com.ua.project.Autobase.dao.carDAO;

import com.ua.project.Autobase.model.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Car
                .builder()
                .id(rs.getLong("id"))
                .model(rs.getString("model"))
                .condition(rs.getInt("condition"))
                .isOnService(rs.getBoolean("is_on_service"))
                .manufacturer(rs.getString("manufacturer"))
                .loadCapacity(rs.getDouble("load_capacity"))
                .build();
    }
}

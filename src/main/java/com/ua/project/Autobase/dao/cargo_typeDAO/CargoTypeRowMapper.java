package com.ua.project.Autobase.dao.cargo_typeDAO;

import com.ua.project.Autobase.model.CargoType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CargoTypeRowMapper implements RowMapper<CargoType> {
    @Override
    public CargoType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CargoType
                .builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .costPerKG(rs.getBigDecimal("cost_per_kg"))
                .requiredExperience(rs.getDouble("required_experience"))
                .build();
    }
}

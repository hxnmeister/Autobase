package com.ua.project.Autobase.dao.applicationDAO;

import com.ua.project.Autobase.model.Application;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ApplicationRowMapper implements RowMapper<Application> {

    @Override
    public Application mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Application
                .builder()
                .id(rs.getLong("id"))
                .weight(rs.getDouble("weight"))
                .cargoTypeId(rs.getInt("cargo_type_id"))
                .build();
    }
}

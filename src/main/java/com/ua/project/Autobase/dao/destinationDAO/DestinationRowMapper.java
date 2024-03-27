package com.ua.project.Autobase.dao.destinationDAO;

import com.ua.project.Autobase.model.Destination;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DestinationRowMapper implements RowMapper<Destination> {
    @Override
    public Destination mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Destination
                .builder()
                .id(rs.getLong("id"))
                .distance(rs.getDouble("distance"))
                .country(rs.getString("country"))
                .city(rs.getString("city"))
                .build();
    }
}

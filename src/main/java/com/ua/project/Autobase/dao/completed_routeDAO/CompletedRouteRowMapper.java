package com.ua.project.Autobase.dao.completed_routeDAO;

import com.ua.project.Autobase.model.CompletedRoute;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CompletedRouteRowMapper implements RowMapper<CompletedRoute> {
    @Override
    public CompletedRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CompletedRoute
                .builder()
                .id(rs.getLong("id"))
                .beginDate(rs.getDate("begin_date"))
                .endDate(rs.getDate("end_date"))
                .routeId(rs.getInt("route_id"))
                .build();
    }
}

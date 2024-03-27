package com.ua.project.Autobase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedRoute {
    private Long id;
    private Date beginDate;
    private Date endDate;
    private int routeId;
}

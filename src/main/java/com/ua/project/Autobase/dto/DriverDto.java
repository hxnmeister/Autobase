package com.ua.project.Autobase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class DriverDto {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal earnings;
    private Double drivingExperience;
}

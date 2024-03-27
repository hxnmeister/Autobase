package com.ua.project.Autobase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private BigDecimal earnings;
    private double drivingExperience;
}

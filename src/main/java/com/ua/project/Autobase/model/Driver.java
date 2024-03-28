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

    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
                "  " + firstName + " " + lastName + "\n" +
                "  Earnings: " + earnings + "\n" +
                "  Driving Experience: " + drivingExperience + "\n" +
                "-".repeat(20);
    }
}

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
public class CargoType {
    private Long id;
    private String title;
    private BigDecimal costPerKG;
    private double requiredExperience;

    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
                "  Cargo: " + title + "\n" +
                "  Cost Per KG: " + costPerKG + "$\n" +
                "  Required Experience: " + requiredExperience + "years\n" +
                "-".repeat(20);
    }
}

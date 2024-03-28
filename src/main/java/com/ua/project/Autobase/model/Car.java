package com.ua.project.Autobase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String model;
    private int condition;
    private boolean isOnService;
    private String manufacturer;
    private double loadCapacity;

    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
                "  Manufacturer: " + manufacturer + "\n" +
                "  Make: " + model + "\n" +
                "  Condition: " + condition + "%\n" +
                "  Is On Service: " + (isOnService ? "yes" : "no") + "\n" +
                "  Load Capacity: " + loadCapacity + "kg\n" +
                "-".repeat(20);
    }
}

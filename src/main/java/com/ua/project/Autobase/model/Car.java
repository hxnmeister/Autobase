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
}

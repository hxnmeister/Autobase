package com.ua.project.Autobase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CarDto {
    private Long id;
    private String model;
    private Integer condition;
    private Integer isOnService;
    private String manufacturer;
    private Double loadCapacity;
}

package com.ua.project.Autobase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApplicationDto {
    private Long id;
    private Double weight;
    private Long cargoTypeId;
}

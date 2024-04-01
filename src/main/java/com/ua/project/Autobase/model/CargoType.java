package com.ua.project.Autobase.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "cargo_types")
public class CargoType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost_per_kg")
    private BigDecimal costPerKG;

    @Column(name = "required_experience")
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

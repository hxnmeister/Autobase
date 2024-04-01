package com.ua.project.Autobase.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight")
    private double weight;

    @ManyToOne
    @JoinColumn(name = "cargo_type_id", nullable = false)
    private CargoType cargoType;

    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
                "  Cargo Weight: " + weight + "\n" +
                "  Cargo Title: " + cargoType.getTitle() + "\n" +
                "  Cost Per KG: " + cargoType.getCostPerKG() + "$\n" +
                "  Required Experience: " + cargoType.getRequiredExperience() + " years\n" +
                "-".repeat(20);
    }
}

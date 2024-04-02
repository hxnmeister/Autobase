package com.ua.project.Autobase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "condition")
    private int condition;

    @Column(name = "is_on_service")
    private int isOnService;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "load_capacity")
    private double loadCapacity;

    @Override
    public String toString() {
        return "  ID: " + id + "\n" +
                "  Manufacturer: " + manufacturer + "\n" +
                "  Make: " + model + "\n" +
                "  Condition: " + condition + "%\n" +
                "  Is On Service: " + (isOnService == 1 ? "yes" : "no") + "\n" +
                "  Load Capacity: " + loadCapacity + " kg\n" +
                "-".repeat(20) + "\n";
    }
}

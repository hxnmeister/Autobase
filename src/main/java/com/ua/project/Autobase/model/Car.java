package com.ua.project.Autobase.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "cars")
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
    private boolean isOnService;

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
                "  Is On Service: " + (isOnService ? "yes" : "no") + "\n" +
                "  Load Capacity: " + loadCapacity + "kg\n" +
                "-".repeat(20);
    }
}

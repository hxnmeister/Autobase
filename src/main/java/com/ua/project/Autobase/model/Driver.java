package com.ua.project.Autobase.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "earnings")
    private BigDecimal earnings;

    @Column(name = "driving_experience")
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

package com.ua.project.Autobase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "drivers")
@AllArgsConstructor
@NoArgsConstructor
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

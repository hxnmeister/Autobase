package com.ua.project.Autobase.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}

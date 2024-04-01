package com.ua.project.Autobase.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "distance")
    double distance;

    @Column(name = "country")
    String country;

    @Column(name = "city")
    String city;
}

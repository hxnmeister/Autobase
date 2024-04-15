package com.ua.project.Autobase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.sql.Date;
import javax.persistence.*;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "completed_routes")
@AllArgsConstructor
@NoArgsConstructor
public class CompletedRoute {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}

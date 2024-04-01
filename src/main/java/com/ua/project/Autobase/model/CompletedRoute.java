package com.ua.project.Autobase.model;

import lombok.Data;
import java.sql.Date;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "completed_routes")
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
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;
}

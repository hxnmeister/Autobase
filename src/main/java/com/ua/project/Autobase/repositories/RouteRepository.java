package com.ua.project.Autobase.repositories;

import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r.car FROM Route r WHERE r.driver.id = :driverId")
    Car findDriversCar(@Param("driverId") Long driverId);
}

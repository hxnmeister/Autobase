package com.ua.project.Autobase.repositories;

import com.ua.project.Autobase.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
    String SELECT_CARS_WITH_REQUIRED_CAPACITY_AND_NOT_ON_ROUTE = """
        SELECT c.*
        FROM cars c
        LEFT JOIN routes r ON c.id = r.car_id
        WHERE r.car_id IS NULL AND c.load_capacity > :requiredCapacity
    """;

    @Query(value = SELECT_CARS_WITH_REQUIRED_CAPACITY_AND_NOT_ON_ROUTE, nativeQuery = true)
    List<Car> getCarsByLoadCapacityIsGreaterThanAndNotOnRoute(@Param("requiredCapacity") Double requiredCapacity);
}

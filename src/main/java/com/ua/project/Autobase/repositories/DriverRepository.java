package com.ua.project.Autobase.repositories;

import com.ua.project.Autobase.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver, Long> {
    String SELECT_DRIVERS_WITH_REQUIRED_EXPERIENCE_AND_NOT_ON_ROUTE = """
        SELECT d.*
        FROM drivers d
        LEFT JOIN routes r ON d.id = r.driver_id
        WHERE r.driver_id IS NULL AND d.driving_experience > :requiredExperience
    """;

    @Query(value = SELECT_DRIVERS_WITH_REQUIRED_EXPERIENCE_AND_NOT_ON_ROUTE, nativeQuery = true)
    List<Driver> getDriversByDrivingExperienceIsGreaterThanAndNotOnRoute(@Param("requiredExperience") Double requiredExperience);

    Optional<Driver> findDriverById(Long id);
}

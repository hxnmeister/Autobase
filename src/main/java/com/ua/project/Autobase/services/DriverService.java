package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.services.CRUDInterface;

import java.util.List;
import java.util.Optional;

public interface DriverService extends CRUDInterface<Driver> {
    List<Driver> getDriversByDrivingExperienceIsGreaterThanAndNotOnRoute(Double requiredExperience);

    Optional<Driver> findDriverById(Long id);
}

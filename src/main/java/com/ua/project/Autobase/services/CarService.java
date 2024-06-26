package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.services.CRUDInterface;

import java.util.List;

public interface CarService extends CRUDInterface<Car> {
    List<Car> getCarsByLoadCapacityIsGreaterThanAndNotOnRoute(Double requiredCapacity);

    Car changeCarCondition(Car currentRouteCar);
}

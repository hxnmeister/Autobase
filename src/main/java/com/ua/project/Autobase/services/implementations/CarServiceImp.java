package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.CarRepository;
import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CarServiceImp implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car item) {
        return carRepository.save(item);
    }

    @Override
    public Car update(Car item) {
        return carRepository.save(item);
    }

    @Override
    public void delete(Car item) {
        carRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        carRepository.deleteAll();
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> saveMany(List<Car> itemsList) {
        return carRepository.saveAll(itemsList);
    }

    @Override
    public List<Car> getCarsByLoadCapacityIsGreaterThanAndNotOnRoute(Double requiredCapacity) {
        return carRepository.getCarsByLoadCapacityIsGreaterThanAndNotOnRoute(requiredCapacity);
    }

    @Override
    public Car changeCarCondition(Car currentRouteCar) {
        if (currentRouteCar.getIsOnService() == 1) {
            currentRouteCar.setCondition(currentRouteCar.getCondition() + ThreadLocalRandom.current().nextInt(2, 15));

            if (currentRouteCar.getCondition() > 80) {
                currentRouteCar.setIsOnService(0);
            }
        }
        else {
            currentRouteCar.setCondition(currentRouteCar.getCondition() - ThreadLocalRandom.current().nextInt(2, 8));

            if (currentRouteCar.getCondition() < 20) {
                currentRouteCar.setIsOnService(1);
            }
        }

        return currentRouteCar;
    }
}

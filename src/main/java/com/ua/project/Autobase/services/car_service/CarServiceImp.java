package com.ua.project.Autobase.services.car_service;

import com.ua.project.Autobase.dao.carDAO.CarRepository;
import com.ua.project.Autobase.models.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

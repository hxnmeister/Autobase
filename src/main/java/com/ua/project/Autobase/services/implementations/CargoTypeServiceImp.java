package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.CargoTypeRepository;
import com.ua.project.Autobase.models.CargoType;
import com.ua.project.Autobase.services.CargoTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoTypeServiceImp implements CargoTypeService {
    private final CargoTypeRepository cargoTypeRepository;

    @Override
    public CargoType save(CargoType item) {
        return cargoTypeRepository.save(item);
    }

    @Override
    public CargoType update(CargoType item) {
        return cargoTypeRepository.save(item);
    }

    @Override
    public void delete(CargoType item) {
        cargoTypeRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        cargoTypeRepository.deleteAll();
    }

    @Override
    public List<CargoType> findAll() {
        return cargoTypeRepository.findAll();
    }

    @Override
    public List<CargoType> saveMany(List<CargoType> itemsList) {
        return cargoTypeRepository.saveAll(itemsList);
    }

    @Override
    public CargoType findCargoTypeById(long id) {
        return cargoTypeRepository.findCargoTypeById(id);
    }
}

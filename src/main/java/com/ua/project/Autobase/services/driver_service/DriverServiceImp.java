package com.ua.project.Autobase.services.driver_service;

import com.ua.project.Autobase.dao.driverDAO.DriverRepository;
import com.ua.project.Autobase.models.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImp implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public Driver save(Driver item) {
        return driverRepository.save(item);
    }

    @Override
    public Driver update(Driver item) {
        return driverRepository.save(item);
    }

    @Override
    public void delete(Driver item) {
        driverRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        driverRepository.deleteAll();
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public List<Driver> saveMany(List<Driver> itemsList) {
        return driverRepository.saveAll(itemsList);
    }
}

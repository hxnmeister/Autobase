package com.ua.project.Autobase.services.route_service;

import com.ua.project.Autobase.dao.routeDAO.RouteRepository;
import com.ua.project.Autobase.models.Car;
import com.ua.project.Autobase.models.Driver;
import com.ua.project.Autobase.models.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImp implements RouteService {
    private final RouteRepository routeRepository;

    @Override
    public Route save(Route item) {
        return routeRepository.save(item);
    }

    @Override
    public Route update(Route item) {
        return routeRepository.save(item);
    }

    @Override
    public void delete(Route item) {
        routeRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        routeRepository.deleteAll();
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public List<Route> saveMany(List<Route> itemsList) {
        return routeRepository.saveAll(itemsList);
    }
}

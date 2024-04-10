package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.RouteRepository;
import com.ua.project.Autobase.models.Route;
import com.ua.project.Autobase.services.RouteService;
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

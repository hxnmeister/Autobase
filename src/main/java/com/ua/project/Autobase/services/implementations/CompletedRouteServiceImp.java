package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.CompletedRouteRepository;
import com.ua.project.Autobase.models.CompletedRoute;
import com.ua.project.Autobase.services.CompletedRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompletedRouteServiceImp implements CompletedRouteService {
    private final CompletedRouteRepository completedRouteRepository;

    @Override
    public CompletedRoute save(CompletedRoute item) {
        return completedRouteRepository.save(item);
    }

    @Override
    public CompletedRoute update(CompletedRoute item) {
        return completedRouteRepository.save(item);
    }

    @Override
    public void delete(CompletedRoute item) {
        completedRouteRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        completedRouteRepository.deleteAll();
    }

    @Override
    public List<CompletedRoute> findAll() {
        return completedRouteRepository.findAll();
    }

    @Override
    public List<CompletedRoute> saveMany(List<CompletedRoute> itemsList) {
        return completedRouteRepository.saveAll(itemsList);
    }
}

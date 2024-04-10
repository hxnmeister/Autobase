package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.ApplicationRepository;
import com.ua.project.Autobase.models.Application;
import com.ua.project.Autobase.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImp implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public Application save(Application item) {
         return applicationRepository.save(item);
    }

    @Override
    public Application update(Application item) {
        return applicationRepository.save(item);
    }

    @Override
    public void delete(Application item) {
        applicationRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        applicationRepository.deleteAll();
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> saveMany(List<Application> itemsList) {
        return applicationRepository.saveAll(itemsList);
    }
}

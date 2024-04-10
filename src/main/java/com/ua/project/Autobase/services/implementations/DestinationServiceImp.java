package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.repositories.DestinationRepository;
import com.ua.project.Autobase.models.Destination;
import com.ua.project.Autobase.services.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationServiceImp implements DestinationService {
    private final DestinationRepository destinationRepository;

    @Override
    public Destination save(Destination item) {
        return destinationRepository.save(item);
    }

    @Override
    public Destination update(Destination item) {
        return destinationRepository.save(item);
    }

    @Override
    public void delete(Destination item) {
        destinationRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        destinationRepository.deleteAll();
    }

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public List<Destination> saveMany(List<Destination> itemsList) {
        return destinationRepository.saveAll(itemsList);
    }
}

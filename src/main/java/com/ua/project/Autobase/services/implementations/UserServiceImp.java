package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.User;
import com.ua.project.Autobase.repositories.UserRepository;
import com.ua.project.Autobase.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User item) {
        return userRepository.save(item);
    }

    @Override
    public User update(User item) {
        return userRepository.save(item);
    }

    @Override
    public void delete(User item) {
        userRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> saveMany(List<User> itemsList) {
        return userRepository.saveAll(itemsList);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}

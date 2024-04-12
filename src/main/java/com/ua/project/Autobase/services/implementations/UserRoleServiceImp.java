package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.UserRole;
import com.ua.project.Autobase.repositories.UserRoleRepository;
import com.ua.project.Autobase.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImp implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public Optional<List<String>> getUserRolesByUserId(Long userId) {
        return userRoleRepository.getUserRolesByUserId(userId);
    }
}

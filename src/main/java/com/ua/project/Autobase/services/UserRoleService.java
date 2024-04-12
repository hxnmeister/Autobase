package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    Optional<List<String>> getUserRolesByUserId(Long userId);
}

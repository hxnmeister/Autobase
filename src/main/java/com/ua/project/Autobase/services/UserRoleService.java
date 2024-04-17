package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.User;
import com.ua.project.Autobase.models.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleService extends CRUDInterface<UserRole> {
    Optional<List<String>> getUserRolesByUserId(Long userId);

    List<UserRole> saveUsersAndApplyRole(List<User> users, String role);

    UserRole saveUserAndApplyRole(User user, String role);
}

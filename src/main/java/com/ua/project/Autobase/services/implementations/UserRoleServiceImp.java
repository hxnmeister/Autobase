package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.Role;
import com.ua.project.Autobase.models.User;
import com.ua.project.Autobase.models.UserRole;
import com.ua.project.Autobase.repositories.RoleRepository;
import com.ua.project.Autobase.repositories.UserRepository;
import com.ua.project.Autobase.repositories.UserRoleRepository;
import com.ua.project.Autobase.services.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImp implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserRole save(UserRole item) {
        return userRoleRepository.save(item);
    }

    @Override
    public UserRole update(UserRole item) {
        return userRoleRepository.save(item);
    }

    @Override
    public void delete(UserRole item) {
        userRoleRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        userRoleRepository.deleteAll();
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> saveMany(List<UserRole> itemsList) {
        return userRoleRepository.saveAll(itemsList);
    }

    @Override
    public Optional<List<String>> getUserRolesByUserId(Long userId) {
        return userRoleRepository.getUserRolesByUserId(userId);
    }

    @Override
    public List<UserRole> saveUsersAndApplyRole(List<User> users, String role) {
        List<UserRole> userRoleList = new ArrayList<>();
        List<User> savedUsers = userRepository.saveAll(users);
        Role savedRole = roleRepository.save(Role.builder().title(role).build());

        savedUsers.forEach((savedUser) -> userRoleList.add(UserRole.builder().user(savedUser).role(savedRole).build()));

        return userRoleRepository.saveAll(userRoleList);
    }

    @Override
    public UserRole saveUserAndApplyRole(User user, String role) {
        User savedUser = userRepository.save(user);
        Role savedRole = roleRepository.save(Role.builder().title(role).build());

        return userRoleRepository.save(UserRole.builder().user(savedUser).role(savedRole).build());
    }
}

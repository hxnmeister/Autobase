package com.ua.project.Autobase.services.implementations;

import com.ua.project.Autobase.models.Role;
import com.ua.project.Autobase.repositories.RoleRepository;
import com.ua.project.Autobase.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role item) {
        return roleRepository.save(item);
    }

    @Override
    public Role update(Role item) {
        return roleRepository.save(item);
    }

    @Override
    public void delete(Role item) {
        roleRepository.delete(item);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> saveMany(List<Role> itemsList) {
        return roleRepository.saveAll(itemsList);
    }
}

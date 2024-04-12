package com.ua.project.Autobase.repositories;

import com.ua.project.Autobase.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select ur.role.title from UserRole ur where ur.user.id = ?1")
    Optional<List<String>> getUserRolesByUserId(Long userId);
}

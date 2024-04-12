package com.ua.project.Autobase.services;

import com.ua.project.Autobase.models.User;

import java.util.Optional;

public interface UserService extends CRUDInterface<User> {
    Optional<User> findUserByLogin(String login);
}

package com.ua.project.Autobase.exceptions;

import com.ua.project.Autobase.models.Application;

public class CannotAddRouteException extends RuntimeException {
    public CannotAddRouteException(String message) {
        super(message);
    }

    public CannotAddRouteException() {
        this("Cannot add data to Routes!");
    }
}

package com.ua.project.Autobase.exceptions;

public class RequiredParamIsEmptyException extends RuntimeException {
    public RequiredParamIsEmptyException(String paramName) {
        super(paramName + " is empty!");
    }
}

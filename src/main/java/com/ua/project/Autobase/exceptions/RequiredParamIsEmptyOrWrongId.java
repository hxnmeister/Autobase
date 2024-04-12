package com.ua.project.Autobase.exceptions;

public class RequiredParamIsEmptyOrWrongId extends RuntimeException {
    public RequiredParamIsEmptyOrWrongId(String paramName) {
        super(paramName + " is empty or id is incorrect!");
    }
}

package com.ua.project.Autobase.exceptions;

public class RequiredParamIsEmptyOrLessException extends RuntimeException {
    public RequiredParamIsEmptyOrLessException(String paramName, String minValue) {
        super(paramName + " is empty or less than " + minValue + "!");
    }
}

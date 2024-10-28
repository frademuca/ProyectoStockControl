package com.es.stockcontrol.utils;

public class Validator {

    private static final String regexNombre = "[a-zA-Z0-9]{1,20}$";
    public static boolean validateName(String username) {
        return !username.matches(regexNombre);
    }
    public static boolean validateNonEmpty(String input) {
        return (input == null || input.trim().isEmpty());
    }

}
package com.vognev.textilewebproject.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * textilewebproject  22/09/2021-17:23
 */
public class ControllerUtils {

    static Map<String, String> getErrors(BindingResult bindingResult) {

        return bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        ));
    }
}

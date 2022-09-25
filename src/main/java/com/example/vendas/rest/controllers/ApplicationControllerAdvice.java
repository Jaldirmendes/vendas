package com.example.vendas.rest.controllers;

import com.example.vendas.exception.RegraNegocioException;
import com.example.vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String messageErrror = ex.getMessage();
        return new ApiErrors(messageErrror);
    }
}

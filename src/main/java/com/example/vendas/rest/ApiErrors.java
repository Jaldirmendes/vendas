package com.example.vendas.rest;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public class ApiErrors {

    @Getter
    public List<String> errors;

    public ApiErrors(String messageError) {
        this.errors = Arrays.asList(messageError);
    }


}

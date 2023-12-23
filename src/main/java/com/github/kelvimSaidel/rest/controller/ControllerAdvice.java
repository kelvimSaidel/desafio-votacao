package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.rest.dto.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidExceptionn(MethodArgumentNotValidException ex ){
        List<String> erros  = ex.getBindingResult().getAllErrors().
                stream().
                map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(erros);
    }


}

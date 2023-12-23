package com.github.kelvimSaidel.rest.dto;

import lombok.Getter;

import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> erros;

    public ApiErrors(List<String> erros){
        this.erros=erros;
    }

}

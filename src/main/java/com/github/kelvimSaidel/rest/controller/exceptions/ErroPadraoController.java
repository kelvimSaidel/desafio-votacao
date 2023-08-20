package com.github.kelvimSaidel.rest.controller.exceptions;



public class ErroPadraoController extends RuntimeException {

    public ErroPadraoController(String msg) {
        super(msg);
    }
}

package com.github.kelvimSaidel.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ObjectNotfoundController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroFormat> ObjetoNaoEncontrado(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus sts = HttpStatus.NOT_FOUND;
        ErroFormat objetoNaoEncontrado = new ErroFormat(System.currentTimeMillis(),sts.value(),"Objeto nao encontrado",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(sts).body(objetoNaoEncontrado);
    }


}

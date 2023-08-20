package com.github.kelvimSaidel.rest.controller.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroController> UsuarioNaoEncontrado(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus sts = HttpStatus.NOT_FOUND;
        ErroController usuarioNaoEncontrado = new ErroController(System.currentTimeMillis(),sts.value(),"Usuario Nao encontrado",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(sts).body(usuarioNaoEncontrado);
    }

    public ResponseEntity<ErroController> PautaNaoEncontrado(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus sts = HttpStatus.NOT_FOUND;
        ErroController pautaNaoEncontrado = new ErroController(System.currentTimeMillis(),sts.value(),"Pauta nao encontrada",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(sts).body(pautaNaoEncontrado);
    }


    public ResponseEntity<ErroController> SessaoNaoEncontrado(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus sts = HttpStatus.NOT_FOUND;
        ErroController sessaoNaoEncontrado = new ErroController(System.currentTimeMillis(),sts.value(),"Sessao nao encontrada",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(sts).body(sessaoNaoEncontrado);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroController> VotoInvalido(Exception e, HttpServletRequest request, Integer id){
        HttpStatus sts = HttpStatus.BAD_REQUEST;
        ErroController votoInvalido = new ErroController(System.currentTimeMillis(),sts.value(),"Voto do usuario "+id+" já computado",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(sts).body(votoInvalido);
    }



    public ResponseEntity<ErroController> SessaoFechada(Exception e, HttpServletRequest request, Integer id){
        HttpStatus sts = HttpStatus.BAD_REQUEST;
        ErroController sessaoFechada = new ErroController(System.currentTimeMillis(),sts.value(),"Sessao Fechada",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(sts).body(sessaoFechada);
    }

}

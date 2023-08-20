package com.github.kelvimSaidel.rest.controller;

import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.RegistroVotosUsuarios;
import com.github.kelvimSaidel.domain.repository.RegistroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RegistroUsuarioController {


    @Autowired
    private RegistroUsuarioRepository registroVotosUsuarios;

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroVotosUsuarios cadastrarVoto(@RequestBody RegistroVotosUsuarios voto){
        return  registroVotosUsuarios.save(voto);

    }
}

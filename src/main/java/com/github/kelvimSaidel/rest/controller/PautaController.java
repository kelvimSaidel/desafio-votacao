package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Pauta")
public class PautaController {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static String pautaNaoEncontrada = "Nenhuma pauta encotrada";
    @RequestMapping(value = "/PautasCadastradas",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Pauta> retornaPauta(){
        List<Pauta> todosPauta = pautaRepository.findAll();
        if (todosPauta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,pautaNaoEncontrada);
        }
        return todosPauta;
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Pauta retornaPautaPorId(@PathVariable("id") Integer id){
        return pautaRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,pautaNaoEncontrada));
    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta cadastraPauta(@RequestBody Pauta pauta){
        if ((pauta.getId_pauta() != null)) {
            if (pautaRepository.existsById(pauta.getId_pauta()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pauta ja registrada");
            }
            if (!usuarioRepository.existsById(pauta.getUsuario().getId_usuario())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não cadastrado");
            }

        return  pautaRepository.save(pauta);
       }


    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta atualizaPauta(@RequestBody Pauta pauta){
        if (!pautaRepository.existsById(pauta.getId_pauta())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pauta não registrada");
        }
        if (!usuarioRepository.existsById(pauta.getUsuario().getId_usuario())) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não cadastrado");
        }
        return pautaRepository.save(pauta);
    }

    @RequestMapping(value ="{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePauta(@PathVariable("id") Integer id){
        if (pautaRepository.findById(id).isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,pautaNaoEncontrada);
        }
        pautaRepository.deleteById(id);
    }



}

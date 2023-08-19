package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/Pauta")
public class PautaController {

    @Autowired
    private PautaRepository pautaRepository;
    @RequestMapping(value = "/PautasCadastradas",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Pauta> retornaPauta(){
        List<Pauta> todosPauta = pautaRepository.findAll();
        if (todosPauta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum pauta encontrado");
        }
        return todosPauta;
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Pauta retornaPautaPorId(@PathVariable("id") Integer id){
        return pautaRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta cadastraPauta(@RequestBody Pauta pauta){
        return  pautaRepository.save(pauta);

    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta atualizaPauta(@RequestBody Pauta pauta){
        return pautaRepository.save(pauta);
    }

    @RequestMapping(value ="{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePauta(@PathVariable("id") Integer id){
        if (pautaRepository.findById(id).isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        pautaRepository.deleteById(id);
    }

//    @RequestMapping(method= RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Pauta atualizaPauta(@RequestBody String nome, Integer id, Long cpf){
//        return pautaRepository.atualizaPauta(id,nome,cpf);
//    }


}

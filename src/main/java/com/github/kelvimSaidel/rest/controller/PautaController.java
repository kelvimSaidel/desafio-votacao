package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import com.github.kelvimSaidel.rest.dto.PautaDto;
import com.github.kelvimSaidel.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping(value = "/Pauta")
public class PautaController {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private PautaService pautaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static String pautaNaoEncontrada = "Nenhuma pauta encotrada";

    @RequestMapping(value = "/Pautas",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<PautaDto> retornaPauta(){
        List<Pauta> todosPauta = pautaRepository.findAll();
        return pautaService.retornaPauta(todosPauta);
    }

    @RequestMapping(value = "/Pauta/{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public PautaDto retornaPautaPorId(@PathVariable("id") Integer id){
        return pautaService.retornaPautaPorId(id);
    }

        @RequestMapping(value = "/Pauta",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public PautaDto cadastraPauta(@RequestBody PautaDto pauta){
        if ((pauta.getId_pauta() != null)) {
            if (pautaRepository.existsById(pauta.getId_pauta()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pauta ja registrada");
            }
            if (!usuarioRepository.existsById(pauta.getId_usuario())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não cadastrado");
            }

        return pautaService.salvarPauta(pauta);
       }


    @RequestMapping(value = "/Pauta",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public PautaDto atualizaPauta(@RequestBody PautaDto pauta){
        if (!pautaRepository.existsById(pauta.getId_pauta())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pauta não registrada");
        }
        if (!usuarioRepository.existsById(pauta.getId_usuario())) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não cadastrado");
        }
        return pautaService.atualizarPauta(pauta);
    }

    @RequestMapping(value ="/Pauta/{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePauta(@PathVariable("id") Integer id){
        if (pautaRepository.findById(id).isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,pautaNaoEncontrada);
        }
        pautaRepository.deleteById(id);
    }



}

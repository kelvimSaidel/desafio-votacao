package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.RegistroVotosUsuarios;
import com.github.kelvimSaidel.domain.entity.Sessao;
import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.enums.StatusSessao;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.RegistroUsuarioRepository;
import com.github.kelvimSaidel.domain.repository.SessaoRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/Sessao")
public class SessaoController {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private RegistroUsuarioRepository rur;

    private static String sessaoNaoencontrada = "Nenhuma sessao encontrada";


    @RequestMapping(value = "/Sessoes",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<Sessao>  retornaSessao(){
        List<Sessao> todosSessao = sessaoRepository.findAll();

        if (todosSessao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,sessaoNaoencontrada);
        }

        return  todosSessao;
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Sessao retornaSessaoPorId(@PathVariable("id") Integer id){
        return sessaoRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,sessaoNaoencontrada));
    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Sessao cadastraSessao(@RequestBody Sessao sessao){
        if ((sessao.getId_sessao() != null)) {
            if (sessaoRepository.existsById(sessao.getId_sessao()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Sessao ja registrada");
        }
            return  sessaoRepository.save(sessao);

    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Sessao atualizaSessao(@RequestBody Sessao Sessao){
        return sessaoRepository.save(Sessao);
    }

    @RequestMapping(value ="{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSessao(@PathVariable("id") Integer id){
        if (sessaoRepository.findById(id).isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,sessaoNaoencontrada);
        }
        sessaoRepository.deleteById(id);
    }

    @RequestMapping(value= "/Votar/Usuario/{id}/Pauta/{pauta}/voto/{voto}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Sessao> votar(@PathVariable("id") Integer id_usuario,@PathVariable("pauta") Integer id_pauta, @PathVariable("voto")  String voto) {

        voto = Normalizer.normalize(voto,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Boolean verificaUsuario = usuarioRepository.existsById(id_usuario);
        Integer validaUsuario = rur.validaUsuarioJavotou(id_usuario,id_pauta);
        Sessao sessao1 = sessaoRepository.retornaSessao(id_pauta);
        Pauta pauta = pautaRepository.retornarPautas(id_pauta);

        Integer valorAntigo = 0;
         System.out.println(validaUsuario);
        if (sessao1==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pauta ou sessao inexistente");
        }

        if (validaUsuario > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Voto já computado para o usuario "+id_usuario);
        }

        if (!verificaUsuario) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuario encontrado");
        }

        if (sessao1.getStatus().equals(StatusSessao.FECHADA)){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sessao Fechada");
        }

        if (voto.equalsIgnoreCase("SIM")){
             valorAntigo = sessao1.getSim();

            rur.save(new RegistroVotosUsuarios(id_usuario, voto, pauta));

            sessao1.setSim(valorAntigo+1);
            sessaoRepository.save(sessao1);
        }else if (voto.equalsIgnoreCase("NAO")){
            valorAntigo = sessao1.getNao();

            rur.save(new RegistroVotosUsuarios(id_usuario, voto, pauta));

            sessao1.setNao(valorAntigo+1);
            sessaoRepository.save(sessao1);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto invalido digite somente SIM ou NAO");
        }

        return sessaoRepository.findById(sessao1.getId_sessao());


    }



}




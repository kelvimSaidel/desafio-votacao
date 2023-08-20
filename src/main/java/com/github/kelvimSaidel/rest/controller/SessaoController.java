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


    @RequestMapping(value = "/Sessoes",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<Sessao>  retornaSessao(){
        List<Sessao> todosSessao = sessaoRepository.findAll();

        if (todosSessao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum Sessao encontrado");
        }

        return  todosSessao;
    }

    @RequestMapping(value = "{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Sessao retornaSessaoPorId(@PathVariable("id") Integer id){
        return sessaoRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Sessao cadastraSessao(@RequestBody Sessao Sessao){
        return  sessaoRepository.save(Sessao);

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        sessaoRepository.deleteById(id);
    }

    @RequestMapping(value= "/Votar/Usuario/{id}/Pauta/{pauta}/voto/{voto}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public String votar(@PathVariable("id") Integer id_usuario,@PathVariable("pauta") Integer id_pauta, @PathVariable("voto")  String voto) {

        voto = Normalizer.normalize(voto,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Boolean verificaUsuario = usuarioRepository.existsById(id_usuario);
        Boolean verificaPauta = pautaRepository.existsById(id_pauta);
        Integer validaUsuario = rur.validaUsuarioJavotou(id_usuario,id_pauta);
        Sessao sessao = sessaoRepository.retornaSessao(id_pauta);
        Pauta pauta = pautaRepository.retornarPautas(id_pauta);

        Integer valorAntigo = 0;
         System.out.println(validaUsuario);
        if (sessao==null) {
            return "Sessao Inválida";
        }

        if (validaUsuario > 0){
            return "Voto invalido.";
        }

        if (!verificaUsuario) {
            return "Usuário invalido";
        }
        if (!verificaPauta) {
            return "Pauta invalida";
        }

        if (sessao.getStatus().equals(StatusSessao.FECHADA)){
            return "Sessão Fechada";
        }

        if (voto.equalsIgnoreCase("SIM")){
             valorAntigo = sessao.getSim();

            rur.save(new RegistroVotosUsuarios(id_usuario, voto, pauta));

            sessao.setSim(valorAntigo+1);
           sessaoRepository.save(sessao);
        }else if (voto.equalsIgnoreCase("NAO")){
            valorAntigo = sessao.getNao();

            rur.save(new RegistroVotosUsuarios(id_usuario, voto, pauta));
            sessao.setNao(valorAntigo+1);
            sessaoRepository.save(sessao);
        } else {
            return "Voto Invalido";
        }

        return id_usuario+" "+id_pauta+" "+voto+" "+verificaUsuario+" "+verificaPauta+" "+sessao.toString();



    }

//    @GetMapping(value="/BuscaSessaoPorNome")
//    public List<Sessao> filtro(String filtro){
//        ExampleMatcher exampExampleMatcherle =
//                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//        Example example = Example.of(filtro,exampExampleMatcherle);
//        return sessaoRepository.findAll(example);
//    }

}




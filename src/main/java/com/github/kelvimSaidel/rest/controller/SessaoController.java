package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.RegistroVotosUsuarios;
import com.github.kelvimSaidel.domain.entity.Sessao;
import com.github.kelvimSaidel.domain.enums.StatusSessao;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.RegistroUsuarioRepository;
import com.github.kelvimSaidel.domain.repository.SessaoRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import com.github.kelvimSaidel.rest.dto.SessaoDto;
import com.github.kelvimSaidel.service.SessaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SessaoController {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private RegistroUsuarioRepository rur;

    @Autowired
    private SessaoService sessaoService;

    private static  final String sessaoNaoencontrada = "Nenhuma sessao encontrada";

    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS");

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @RequestMapping(value = "/Sessoes",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    @ResponseBody
    public List<SessaoDto>  retornaSessao(){
        if (sessaoRepository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,sessaoNaoencontrada);

        }

        return  sessaoService.retornarTodasSessoes();
    }

    @RequestMapping(value = "/Sessao/{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public SessaoDto retornaSessaoPorId(@PathVariable("id") Integer id){
        logger.info(" Sessao existe? (retornaSessaoPorId) ");
        if (!sessaoRepository.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,sessaoNaoencontrada);
        }
        return sessaoService.retornarSessaoPorid(id,sessaoNaoencontrada);
    }


    @RequestMapping(value = "/Sessao",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoDto cadastraSessao(@RequestBody SessaoDto sessao){
        if ((sessao.getId_sessao() != null)) {
            if (sessaoRepository.existsById(sessao.getId_sessao()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Sessao ja registrada");
        }
          //Se a vigencia for informada a dt_fechamente recebe a hora local mais a vigencia informada, se nao recebe
          //a hora local mais 1 min.
        if (!pautaRepository.existsById(sessao.getId_pauta())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pauta não registrada");
        }

        sessaoRepository.findAll().stream().peek( verificaPauta -> {
            logger.info("idPauta=idinformado? (cadastraSessao)"+verificaPauta.getPauta().getId_pauta()+" - "+sessao.getId_pauta());
            if (verificaPauta.getPauta().getId_pauta() == sessao.getId_pauta()){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Já existe uma sessao cadastrada com essa pauta.");
           }
        }).collect(Collectors.toList());

        logger.info("nomePauta=nomeInformado? (cadastraSessao)"+pautaRepository.findById(sessao.getId_pauta()).get().getNome_pauta()+" - "+sessao.getNome_pauta());
        if (!pautaRepository.findById(sessao.getId_pauta()).get().getNome_pauta().equals(sessao.getNome_pauta())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pauta não pertence a esse Id.");
        }

        return  sessaoService.salvarSessao(sessao);

    }


    @RequestMapping(value = "/Sessao",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public SessaoDto atualizaSessao(@RequestBody SessaoDto sessao){
        if (!sessaoRepository.existsById(sessao.getId_sessao())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sessao não registrada");
        }
        if (!pautaRepository.existsById(sessao.getId_pauta())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pauta não registrada");
        }

        if (!pautaRepository.findById(sessao.getId_pauta()).get().getNome_pauta().equals(sessao.getNome_pauta())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Nome da pauta não pertence ao id da pauta informada");

        }

        sessaoRepository.findAll().stream().peek( verificaPauta -> {
            logger.info(" 3 "+verificaPauta.getPauta().getId_pauta()+" - "+sessao.getId_pauta());
            if (verificaPauta.getPauta().getId_pauta() == sessao.getId_pauta()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Já existe uma sessao cadastrada com essa pauta.");
            }
        }).collect(Collectors.toList());

        if (sessao.getDt_abertura() != null || sessao.getDt_fechamento() != null || sessao.getStatus() != null
                || sessao.getTempoVigenciaEmMinutos() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Só é possível atualizar o id e o nome da pauta.");
        }

        return sessaoService.atualizarSessao(sessao);
    }

    @RequestMapping(value ="/Sessao/{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSessao(@PathVariable("id") Integer id){
        if (sessaoRepository.findById(id).isEmpty() ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,sessaoNaoencontrada);
        }
        sessaoRepository.deleteById(id);
    }

    @RequestMapping(value= "/Sessao/Votar/Usuario/{id}/Pauta/{pauta}/voto/{voto}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Sessao> votar(@PathVariable("id") Integer id_usuario,@PathVariable("pauta") Integer id_pauta, @PathVariable("voto")  String voto) {
        //retira acentos dos votos
        voto = Normalizer.normalize(voto,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        Boolean verificaUsuario = usuarioRepository.existsById(id_usuario);
        Integer validaUsuario = rur.validaUsuarioJavotou(id_usuario,id_pauta);
        Sessao sessao1 = sessaoRepository.retornaSessao(id_pauta);
        Pauta pauta = pautaRepository.retornarPautas(id_pauta);
        String horaLocal = LocalDateTime.now().format(formatador);

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

        if (horaLocal.compareTo(sessao1.getDt_fechamento())>0){
            sessao1.setStatus(StatusSessao.FECHADA);
            sessaoRepository.save(sessao1);
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sessao Fechada");
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




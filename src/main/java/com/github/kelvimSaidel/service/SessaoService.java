package com.github.kelvimSaidel.service;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.Sessao;
import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.RegistroUsuarioRepository;
import com.github.kelvimSaidel.domain.repository.SessaoRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import com.github.kelvimSaidel.rest.dto.PautaDto;
import com.github.kelvimSaidel.rest.dto.SessaoDto;
import org.hibernate.PropertyAccessException;
import org.hibernate.internal.SessionOwnerBehavior;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PautaRepository pautaRepository;

    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS");


    public SessaoDto salvarSessao(@RequestBody SessaoDto sessao){
        SessaoDto novaSessaDto = new SessaoDto();
        Sessao novaSessao = new Sessao();
        Pauta pautaSessao  = new Pauta();
        Usuario usuarioSessao = new Usuario();

        String verifcaSeTempoVigenciaNull = sessao.getTempoVigenciaEmMinutos();


        pautaRepository.findById(sessao.getId_pauta()).map(pauta -> {
            pautaSessao.setId_pauta(sessao.getId_pauta());
            pautaSessao.setUsuario(pauta.getUsuario());
            pautaSessao.setNome_pauta(sessao.getNome_pauta());
            return pauta; });

        if (verifcaSeTempoVigenciaNull != null) {
            novaSessao.setDt_fechamento(LocalDateTime.now().plusMinutes(Long.parseLong(sessao.getTempoVigenciaEmMinutos())).format(formatador));
            novaSessao.setTempoVigenciaEmMinutos(sessao.getTempoVigenciaEmMinutos());
        } else {
            novaSessao.setTempoVigenciaEmMinutos("1");
        }

        novaSessao.setPauta(pautaSessao);
        Integer idNovaSessao  = sessaoRepository.save(novaSessao).getId_sessao();
        sessaoRepository.findById(idNovaSessao).map( salvarSessao -> { novaSessaDto.setId_sessao(idNovaSessao);
            novaSessaDto.setId_pauta(salvarSessao.getPauta().getId_pauta());
            novaSessaDto.setNome_pauta(salvarSessao.getPauta().getNome_pauta());
            novaSessaDto.setTempoVigenciaEmMinutos(salvarSessao.getTempoVigenciaEmMinutos());
            novaSessaDto.setStatus(salvarSessao.getStatus());
            novaSessaDto.setDt_abertura(salvarSessao.getDt_abertura());
            novaSessaDto.setDt_fechamento(salvarSessao.getDt_fechamento());

            return salvarSessao;
        });

        return  novaSessaDto;
    }

    public SessaoDto atualizarSessao(SessaoDto sessao){
        Pauta novaPauta  = new Pauta();
        SessaoDto sessaoAtualizada = new SessaoDto();

       pautaRepository.findById(sessao.getId_pauta()).map(antigaPauta -> {
           novaPauta.setId_pauta(antigaPauta.getId_pauta());
           novaPauta.setNome_pauta(antigaPauta.getNome_pauta());
           novaPauta.setUsuario(antigaPauta.getUsuario());
               return antigaPauta;        });

           sessaoRepository.findById(sessao.getId_sessao()).map( atualizarSessao -> {
            atualizarSessao.setPauta(novaPauta);
               sessaoRepository.save(atualizarSessao);
            sessaoAtualizada.setId_sessao(atualizarSessao.getId_sessao());
            sessaoAtualizada.setId_pauta(atualizarSessao.getPauta().getId_pauta());
            sessaoAtualizada.setNome_pauta(atualizarSessao.getPauta().getNome_pauta());
            sessaoAtualizada.setStatus(atualizarSessao.getStatus());
            sessaoAtualizada.setDt_abertura(atualizarSessao.getDt_abertura());
            sessaoAtualizada.setDt_fechamento(atualizarSessao.getDt_fechamento());
            sessaoAtualizada.setTempoVigenciaEmMinutos(atualizarSessao.getTempoVigenciaEmMinutos());
        return atualizarSessao;
        });

        return  sessaoAtualizada;
    }

    public SessaoDto retornarSessaoPorid(Integer id_sessao, String sessaoNaoEncontrada){
        SessaoDto sessaoPorid = new SessaoDto();
        sessaoRepository.findById(id_sessao).map( sessaoRetornada -> {
            sessaoPorid.setId_sessao(sessaoRetornada.getId_sessao());
            sessaoPorid.setId_pauta(sessaoRetornada.getPauta().getId_pauta());
            sessaoPorid.setNome_pauta(sessaoRetornada.getPauta().getNome_pauta());
            sessaoPorid.setStatus(sessaoRetornada.getStatus());
            sessaoPorid.setDt_abertura(sessaoRetornada.getDt_abertura());
            sessaoPorid.setDt_fechamento(sessaoRetornada.getDt_fechamento());
            sessaoPorid.setTempoVigenciaEmMinutos(sessaoRetornada.getTempoVigenciaEmMinutos());

            return sessaoRetornada;
        });

        return sessaoPorid;

    }

    public List<SessaoDto> retornarTodasSessoes(){

        return  sessaoRepository.findAll().stream().map( sessaoRetornada -> {
            SessaoDto todasSessoes = new SessaoDto();
            todasSessoes.setId_sessao(sessaoRetornada.getId_sessao());
            todasSessoes.setId_pauta(sessaoRetornada.getPauta().getId_pauta());
            todasSessoes.setNome_pauta(sessaoRetornada.getPauta().getNome_pauta());
            todasSessoes.setStatus(sessaoRetornada.getStatus());
            todasSessoes.setDt_abertura(sessaoRetornada.getDt_abertura());
            todasSessoes.setDt_fechamento(sessaoRetornada.getDt_fechamento());
            todasSessoes.setTempoVigenciaEmMinutos(sessaoRetornada.getTempoVigenciaEmMinutos());

            return  todasSessoes;

        }).collect(Collectors.toList());

    }




}

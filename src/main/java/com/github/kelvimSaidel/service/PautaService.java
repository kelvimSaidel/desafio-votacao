package com.github.kelvimSaidel.service;


import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import com.github.kelvimSaidel.rest.dto.PautaDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static String pautaNaoEncontrada = "Nenhuma pauta encotrada";

    public PautaDto salvarPauta( PautaDto pautaDto){
         Pauta pauta = new Pauta();
         pauta.setNome_pauta(pautaDto.getNome_pauta());
         Usuario usuarioPauta = new Usuario();
         usuarioRepository.findById(pautaDto.getId_usuario()).map(
                 usuario1 -> {
                     usuarioPauta.setNome_usuario(usuario1.getNome_usuario());
                     usuarioPauta.setPautas(usuario1.getPautas());
                     usuarioPauta.setCpf(usuario1.getCpf());
                     usuarioPauta.setId_usuario(usuario1.getId_usuario());
                    return usuarioPauta;
                 });
        pauta.setUsuario(usuarioPauta);
        pautaDto.setId_pauta(pautaRepository.save(pauta).getId_pauta());
      return pautaDto;
    }

    public PautaDto atualizarPauta(PautaDto pautaDto){
        Pauta pauta = new Pauta();
        pauta.setId_pauta(pautaDto.getId_pauta());
        pauta.setNome_pauta(pautaDto.getNome_pauta());
        Usuario usuarioPauta = new Usuario();
        usuarioRepository.findById(pautaDto.getId_usuario()).map(
                usuario1 -> {
                    usuarioPauta.setNome_usuario(usuario1.getNome_usuario());
                    usuarioPauta.setPautas(usuario1.getPautas());
                    usuarioPauta.setCpf(usuario1.getCpf());
                    usuarioPauta.setId_usuario(usuario1.getId_usuario());
                    return usuarioPauta;
                });
        pauta.setUsuario(usuarioPauta);
        pautaRepository.save(pauta);
        return pautaDto;
    }

    public PautaDto retornaPautaPorId(Integer id){
        PautaDto retornaPauta =new PautaDto();
      pautaRepository.findById(id).map(campo -> { retornaPauta.setId_pauta(campo.getId_pauta());
                                                  retornaPauta.setNome_pauta(campo.getNome_pauta());
                                                  retornaPauta.setId_usuario(campo.getUsuario().getId_usuario());
                                             return  campo; }).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,pautaNaoEncontrada));
      return retornaPauta;
    }

    public List<PautaDto> retornaPauta( List<Pauta> todosPauta){

        if (todosPauta.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,pautaNaoEncontrada);
        }

        return todosPauta.stream().map(c ->{PautaDto pautas = new PautaDto(c.getId_pauta(),c.getNome_pauta(),c.getUsuario().getId_usuario());
            return  pautas;

        }).collect(Collectors.toList());

    }
}

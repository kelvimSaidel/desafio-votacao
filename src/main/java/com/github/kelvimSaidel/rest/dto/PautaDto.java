package com.github.kelvimSaidel.rest.dto;

import com.github.kelvimSaidel.domain.entity.Pauta;

public class PautaDto {

    private Integer id_pauta;

    private String nome_pauta;

    private Integer id_usuario;

    public PautaDto() {
    }

    public PautaDto(Integer id_pauta, String nome_pauta, Integer id_usuario) {
        this.id_pauta = id_pauta;
        this.nome_pauta = nome_pauta;
        this.id_usuario = id_usuario;
    }


    public Integer getId_pauta() {
        return id_pauta;
    }

    public void setId_pauta(Integer id_pauta) {
        this.id_pauta = id_pauta;
    }

    public String getNome_pauta() {
        return nome_pauta;
    }

    public void setNome_pauta(String nome_pauta) {
        this.nome_pauta = nome_pauta;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}

package com.github.kelvimSaidel.rest.dto;

public class SessaoDto {

    private Integer id_sessao;
    private Integer id_pauta;
    private Integer Nome_pauta;

    public SessaoDto(Integer id_sessao, Integer id_pauta, Integer nome_pauta) {
        this.id_sessao = id_sessao;
        this.id_pauta = id_pauta;
        Nome_pauta = nome_pauta;
    }

    public Integer getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(Integer id_sessao) {
        this.id_sessao = id_sessao;
    }

    public Integer getId_pauta() {
        return id_pauta;
    }

    public void setId_pauta(Integer id_pauta) {
        this.id_pauta = id_pauta;
    }

    public Integer getNome_pauta() {
        return Nome_pauta;
    }

    public void setNome_pauta(Integer nome_pauta) {
        Nome_pauta = nome_pauta;
    }
}

package com.github.kelvimSaidel.rest.dto;

import com.github.kelvimSaidel.domain.enums.StatusSessao;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SessaoDto {

    private Integer id_sessao;

    @NotNull(message="{id.pauta.obrigatorio}")
    private Integer id_pauta;

    @NotEmpty(message="{nome.pauta.obrigatorio}")
    private String Nome_pauta;

    private StatusSessao status;

    private String dt_abertura;

    private String dt_fechamento;

    private String tempoVigenciaEmMinutos;

    public SessaoDto() {
    }

    public SessaoDto(Integer id_sessao, Integer id_pauta, String nome_pauta, StatusSessao status, String dt_abertura, String dt_fechamento, String tempoVigenciaEmMinutos) {
        this.id_sessao = id_sessao;
        this.id_pauta = id_pauta;
        Nome_pauta = nome_pauta;
        this.status = status;
        this.dt_abertura = dt_abertura;
        this.dt_fechamento = dt_fechamento;
        this.tempoVigenciaEmMinutos = tempoVigenciaEmMinutos;
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

    public String getNome_pauta() {
        return Nome_pauta;
    }

    public void setNome_pauta(String nome_pauta) {
        Nome_pauta = nome_pauta;
    }


    public StatusSessao getStatus() {
        return status;
    }

    public void setStatus(StatusSessao status) {
        this.status = status;
    }

    public String getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(String dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public String getDt_fechamento() {
        return dt_fechamento;
    }

    public void setDt_fechamento(String dt_fechamento) {
        this.dt_fechamento = dt_fechamento;
    }

    public String getTempoVigenciaEmMinutos() {
        return tempoVigenciaEmMinutos;
    }

    public void setTempoVigenciaEmMinutos(String tempoVigenciaEmMinutos) {
        this.tempoVigenciaEmMinutos = tempoVigenciaEmMinutos;
    }

}

package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kelvimSaidel.domain.enums.StatusSessao;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_sessao;


    private  Integer  sim = 0;
    private  Integer  nao = 0;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private  StatusSessao status = StatusSessao.ABERTA;
    private LocalDate dt_abertura = LocalDate.now();
    private LocalDate dt_fechamento;

    @ManyToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    public Sessao() {
    }

    public Sessao(Integer id_sessao, Integer sim, Integer nao, StatusSessao status, LocalDate dt_abertura, LocalDate dt_fechamento, Pauta pauta) {
        this.id_sessao = id_sessao;
        this.sim = sim;
        this.nao = nao;
        this.status = status;
        this.dt_abertura = dt_abertura;
        this.dt_fechamento = dt_fechamento;
        this.pauta = pauta;
    }

    public Integer getId_sessao() {
        return id_sessao;
    }

//    public void setId_sessao(Integer id_sessao) {
//        this.id_sessao = id_sessao;
//    }

    public Integer getSim() {
        return sim;
    }

    public void setSim(Integer sim) {
        this.sim = sim;
    }

    public Integer getNao() {
        return nao;
    }

    public void setNao(Integer nao) {
        this.nao = nao;
    }

    public StatusSessao getStatus() {
        return status;
    }

    public void setStatus(StatusSessao status) {
        this.status = status;
    }

    public LocalDate getDt_abertura() {
        return dt_abertura;
    }

    public void setDt_abertura(LocalDate dt_abertura) {
        this.dt_abertura = dt_abertura;
    }

    public LocalDate getDt_fechamento() {
        return dt_fechamento;
    }

    public void setDt_fechamento(LocalDate dt_fechamento) {
        this.dt_fechamento = dt_fechamento;
    }


    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "id_sessao=" + id_sessao +
                ", sim=" + sim +
                ", nao=" + nao +
                ", status=" + status +
                ", dt_abertura=" + dt_abertura +
                ", dt_fechamento=" + dt_fechamento +
                ", pauta=" + pauta.getId_pauta() +
                '}';
    }
}



package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kelvimSaidel.domain.enums.StatusSessao;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sessao;

    @NonNull
    private  Integer  sim = 0;

    @NonNull
    private  Integer  nao = 0;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private  StatusSessao status = StatusSessao.ABERTA;

    //Atribui a hora local para dt_abertura
    @NonNull
    private String dt_abertura = LocalDateTime.now().format(formatador);
    //Calcula por padrao a hora local mais um minuto, caso nao seja informado um tempo de vigencia
    private String dt_fechamento = LocalDateTime.now().plusMinutes(Long.parseLong("1")).format(formatador);

    @ManyToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @JsonIgnore
    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS");

    private String tempoVigenciaEmMinutos;

    public Sessao() {
    }

    public Sessao(Pauta pauta) {
        this.pauta = pauta;
    }

    public Sessao(Integer id_sessao, StatusSessao status, String tempoVigenciaEmMinutos, Pauta pauta) {
        this.id_sessao = id_sessao;
        this.status = status;
        this.tempoVigenciaEmMinutos = tempoVigenciaEmMinutos;
        this.pauta = pauta;
    }

    public Sessao(String tempoVigenciaEmMinutos, Pauta pauta) {
        this.tempoVigenciaEmMinutos = tempoVigenciaEmMinutos;
        this.pauta = pauta;
    }

    public Integer getId_sessao() {
        return id_sessao;
    }

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

    public String getDt_abertura() {
        return dt_abertura;
    }

    public String getDt_fechamento() {
        return dt_fechamento;
    }

    public void setDt_fechamento(String dt_fechamento) {
        this.dt_fechamento = dt_fechamento;
    }


    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public String getTempoVigenciaEmMinutos() {
        return tempoVigenciaEmMinutos;
    }

    public void setTempoVigenciaEmMinutos(String tempoVigenciaEmMinutos) {
        this.tempoVigenciaEmMinutos = tempoVigenciaEmMinutos;
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



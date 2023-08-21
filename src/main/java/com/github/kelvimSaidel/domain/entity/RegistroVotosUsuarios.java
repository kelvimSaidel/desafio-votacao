package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.h2.constraint.Constraint;

import java.util.List;

@Entity
public class RegistroVotosUsuarios {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_registoVU;

    private  Integer id_usuario;

    private String voto;

    @ManyToOne
    @JoinColumn(name= "id_pauta")
    private Pauta pauta;

    public RegistroVotosUsuarios() {
    }

    public RegistroVotosUsuarios(Integer id_registoVU, Integer id_usuario, Pauta pauta) {
        this.id_registoVU = id_registoVU;
        this.id_usuario = id_usuario;
        this.pauta = pauta;
    }

    public RegistroVotosUsuarios(Integer id_usuario, String voto, Pauta pauta) {
        this.id_usuario = id_usuario;
        this.voto = voto;
        this.pauta = pauta;
    }

    public Integer getId_registoVU() {
        return id_registoVU;
    }

    public void setId_registoVU(Integer id_registoVU) {
        this.id_registoVU = id_registoVU;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "RegistroVotosUsuarios{" +
                "id_registoVU=" + id_registoVU +
                ", id_usuario=" + id_usuario +
                ", pauta=" + pauta.getId_pauta() +
                '}';
    }
}

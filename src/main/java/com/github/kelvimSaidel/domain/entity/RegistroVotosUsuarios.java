package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.h2.constraint.Constraint;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class RegistroVotosUsuarios {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_registoVU;

    private  Integer id_usuario;

    private String voto;

    @ManyToOne
    @JoinColumn(name= "id_pauta")
    private Pauta pauta;


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

}

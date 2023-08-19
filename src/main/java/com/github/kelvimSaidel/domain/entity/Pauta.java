package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_pauta;
    private String nome_pauta;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;



    @OneToOne(mappedBy = "pauta")
    @JsonIgnore
    private Sessao sessao;

    public Pauta() {
    }

    public Pauta(Integer id_pauta, String nome_pauta, Usuario usuario) {
        this.id_pauta = id_pauta;
        this.nome_pauta = nome_pauta;
        this.usuario = usuario;
    }

    public String getNome_pauta() {
        return nome_pauta;
    }
    public void setNome_pauta(String nome_pauta){
        this.nome_pauta = nome_pauta;
    }

    public Integer getId_pauta() {
        return id_pauta;
    }
//    public void setId_pauta(Integer id_pauta) {
//        this.id_pauta = id_pauta;
//    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sessao getSessaoVotacao() {
        return sessao;
    }

    public void setSessaoVotacao(Sessao sessao) {
        this.sessao = sessao;
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id_pauta=" + id_pauta +
                ", name_pauta='" + nome_pauta + '\'' +
                ", usuario=" + usuario +
                ", sessaoVotacao=" + sessao +
                '}';
    }
}

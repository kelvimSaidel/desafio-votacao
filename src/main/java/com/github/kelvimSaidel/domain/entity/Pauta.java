package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pauta;

    @NonNull
    private String nome_pauta;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne(mappedBy = "pauta")
    @JsonIgnore
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta")
    @JsonIgnore
    private List<RegistroVotosUsuarios> registroVotosUsuarios;

    public Pauta() {
    }

    public Pauta(Integer id_pauta, String nome_pauta, Usuario usuario,List<RegistroVotosUsuarios> rvu) {
        this.id_pauta = id_pauta;
        this.nome_pauta = nome_pauta;
        this.usuario = usuario;
        this.registroVotosUsuarios = rvu;

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
    public void setId_pauta(Integer id_pauta) {
        this.id_pauta = id_pauta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public List<RegistroVotosUsuarios> getRegistroVotosUsuarios() {
        return registroVotosUsuarios;
    }

    public void setRegistroVotosUsuarios(List<RegistroVotosUsuarios> registroVotosUsuarios) {
        this.registroVotosUsuarios = registroVotosUsuarios;
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id_pauta=" + id_pauta +
                ", nome_pauta='" + nome_pauta + '\'' +
                ", usuario=" + usuario +
                ", sessao=" + sessao +
                '}';
    }
}

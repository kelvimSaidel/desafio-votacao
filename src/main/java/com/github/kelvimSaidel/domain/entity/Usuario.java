package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    private String nome_usuario;

    @NonNull
    @Column(unique=true)
    private long cpf;

    @JsonIgnore
    @OneToMany( mappedBy = "usuario")
    private List<Pauta> pautas;

    public Usuario() {
    }

    public Usuario(Integer id_usuario, String nome_usuario, long cpf) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.cpf = cpf;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id_usuario + '\'' +
                ", nome='" + nome_usuario + '\'' +
                ", cpf=" + Long.toString(cpf) +
                '}';
    }

    public List<Pauta> getPautas() {
        return pautas;
    }

    public void setPautas(List<Pauta> pautas) {
        this.pautas = pautas;
    }
}

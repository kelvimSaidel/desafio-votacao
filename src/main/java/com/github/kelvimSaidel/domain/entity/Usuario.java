package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    private String nome_usuario;

    @NonNull
    @Column(unique = true)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Pauta> pautas;

//    @Autowired
////    @JsonIgnore
//    private UsuarioRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    public Usuario() {
    }

    public Usuario(Integer id_usuario, String nome_usuario, String cpf) {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id_usuario + '\'' +
                ", nome='" + nome_usuario + '\'' +
                ", cpf=" + cpf +
                '}';
    }

    public List<Pauta> getPautas() {
        return pautas;
    }

    public void setPautas(List<Pauta> pautas) {
        this.pautas = pautas;
    }

    public Boolean validaCpf(String cpf, Long existeCpf) {
        String cpfSoDigitos = cpf.replaceAll("[^0-9]","");


        if (existeCpf > 0) {
            logger.info(cpfSoDigitos+" "+existeCpf);
             return false;
        }

        if (cpfSoDigitos.length() != 11) {
            logger.info(cpfSoDigitos+" "+cpf.length());
            return false;
        } else if (cpfSoDigitos.equals("11111111111")
                || cpfSoDigitos.equals("22222222222")
                || cpfSoDigitos.equals("33333333333")
                || cpfSoDigitos.equals("44444444444")
                || cpfSoDigitos.equals("55555555555")
                || cpfSoDigitos.equals("66666666666")
                || cpfSoDigitos.equals("77777777777")
                || cpfSoDigitos.equals("88888888888")
                || cpfSoDigitos.equals("99999999999")
        ) {
            logger.info(cpfSoDigitos+" 3333");
            return false;

        }
        return true;

    }

    public String formataCpf(String cpf) {
        String cpfSoDigitos = cpf.replaceAll("[^0-9]","");


        String novaFormatCpf = "";
        String[] listacpf = cpfSoDigitos.split("");
        String[] novoFormatCpfList = new String[13];
        novoFormatCpfList[3] = ".";
        novoFormatCpfList[6] = ".";
        novoFormatCpfList[9] = "-";
        for (int i = 0; i < listacpf.length; i++) {
            if (novoFormatCpfList[i] != null) {
                novoFormatCpfList[i] = novoFormatCpfList[i]+listacpf[i];

            }else {
                novoFormatCpfList[i] = listacpf[i];

            }

//            logger.info(novoFormatCpfList[i]);
        }

        for (int l = 0; l < novoFormatCpfList.length; l++){
            if (novoFormatCpfList[l] != null) {
                novaFormatCpf = novaFormatCpf.concat(novoFormatCpfList[l]);
            }
        }
        return novaFormatCpf;

    }

}

package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
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
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @NotEmpty(message = "{nome.usuario.obrigatorio}")
    private String nome_usuario;

    @Column(unique = true)
    @NotEmpty(message ="{cpf.obrigatorio}")
    @CPF(message = "{cpf.invalido}")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Pauta> pautas;


    private static final Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    public Usuario(Integer id_usuario, String nome_usuario, String cpf) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.cpf = cpf;
    }



//    public Boolean validaCpf(String cpf, Long existeCpf) {
//        String cpfSoDigitos = cpf.replaceAll("[^0-9]","");
//
//
//        if (existeCpf > 0) {
//            logger.info(cpfSoDigitos+" "+existeCpf+" 1");
//             return false;
//        }
//
//        if (cpfSoDigitos.length() != 11) {
//            logger.info(cpfSoDigitos+" "+cpf.length()+" 2");
//            return false;
//        } else if (cpfSoDigitos.equals("11111111111")
//                || cpfSoDigitos.equals("22222222222")
//                || cpfSoDigitos.equals("33333333333")
//                || cpfSoDigitos.equals("44444444444")
//                || cpfSoDigitos.equals("55555555555")
//                || cpfSoDigitos.equals("66666666666")
//                || cpfSoDigitos.equals("77777777777")
//                || cpfSoDigitos.equals("88888888888")
//                || cpfSoDigitos.equals("99999999999")
//        ) {
//            logger.info(cpfSoDigitos+"3");
//            return false;
//
//        }
//        return true;
//
//    }

//    public String formataCpf(String cpf) {
//        String cpfSoDigitos = cpf.replaceAll("[^0-9]","");
//
//
//        String novaFormatCpf = "";
//        String[] listacpf = cpfSoDigitos.split("");
//        String[] novoFormatCpfList = new String[13];
//        novoFormatCpfList[3] = ".";
//        novoFormatCpfList[6] = ".";
//        novoFormatCpfList[9] = "-";
//        for (int i = 0; i < listacpf.length; i++) {
//            if (novoFormatCpfList[i] != null) {
//                novoFormatCpfList[i] = novoFormatCpfList[i]+listacpf[i];
//
//            }else {
//                novoFormatCpfList[i] = listacpf[i];
//
//            }
//
////            logger.info(novoFormatCpfList[i]);
//        }
//
//        for (int l = 0; l < novoFormatCpfList.length; l++){
//            if (novoFormatCpfList[l] != null) {
//                novaFormatCpf = novaFormatCpf.concat(novoFormatCpfList[l]);
//            }
//        }
//        return novaFormatCpf;
//
//    }

}

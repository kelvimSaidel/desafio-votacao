package com.github.kelvimSaidel.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pauta;

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

//    public Pauta() {
//    }

    public Pauta(Integer id_pauta, String nome_pauta, Usuario usuario,List<RegistroVotosUsuarios> rvu) {
        this.id_pauta = id_pauta;
        this.nome_pauta = nome_pauta;
        this.usuario = usuario;
        this.registroVotosUsuarios = rvu;

    }

}

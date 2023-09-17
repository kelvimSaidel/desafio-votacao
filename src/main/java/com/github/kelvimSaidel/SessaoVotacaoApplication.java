package com.github.kelvimSaidel;


import com.github.kelvimSaidel.domain.entity.RegistroVotosUsuarios;
import com.github.kelvimSaidel.domain.enums.StatusSessao;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
import com.github.kelvimSaidel.domain.repository.RegistroUsuarioRepository;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import com.github.kelvimSaidel.domain.repository.SessaoRepository;
import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.Sessao;
import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.rest.controller.SessaoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@EnableScheduling
public class SessaoVotacaoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SessaoVotacaoApplication.class,args);


    }
}

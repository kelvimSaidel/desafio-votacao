package com.github.kelvimSaidel;


import com.github.kelvimSaidel.domain.enums.StatusSessao;
import com.github.kelvimSaidel.domain.repository.PautaRepository;
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
import org.springframework.web.bind.annotation.RestController;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class SessaoVotacaoApplication {


    @Bean
    public CommandLineRunner init(@Autowired UsuarioRepository usuarioRepository,
                                  @Autowired PautaRepository pautaRepository,
                                  @Autowired SessaoRepository sessaoRepository,
                                  @Autowired SessaoController sessaoController ){
        return args -> {
            Usuario usuario1 = new Usuario();
            usuario1.setNome_usuario("Kelvim");
            usuario1.setCpf(123456711);
            usuarioRepository.save(usuario1);

//            Usuario usuario2 = new Usuario();
//            usuario2.setNome_usuario("Joao");
//            usuario2.setCpf(123456712);
//            usuarioRepository.save(usuario2);
//
            Pauta pauta1 = new Pauta();
            pauta1.setNome_pauta("Pauta1");
            pauta1.setUsuario(usuario1);

            pautaRepository.save(pauta1);

            LocalDate dia = LocalDate.of(2023, Month.AUGUST, 16);
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Sessao sessao = new Sessao();
            sessao.setPauta(pauta1);
//            sessao.setSim(1);
//            sessao.setNao(1);
              sessao.setStatus(StatusSessao.ABERTA);
//            sessao.setDt_abertura(dia);
//            sessao.setDt_fechamento(LocalDate.parse(formatador.format(dia)));
            sessao.setDt_fechamento(dia);
            sessaoRepository.save(sessao);
//
        // sessaoController.votar(1,1,"NÃO");
//
//
           List<Sessao> sessoes = sessaoRepository.findAll();
//            Optional<Sessao> sessoes1 = sessaoRepository.findById(1);
//            System.out.println(sessoes1);
            sessoes.forEach(System.out::println);
//
           System.out.println(sessoes.toString());
//
////
////            pautaRepository.save(pauta1);
//

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SessaoVotacaoApplication.class,args);


    }
}

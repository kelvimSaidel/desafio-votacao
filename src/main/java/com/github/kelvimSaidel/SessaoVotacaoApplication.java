package com.github.kelvimSaidel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableScheduling
public class SessaoVotacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessaoVotacaoApplication.class,args);


    }
}

package com.github.kelvimSaidel.service.scheduleService;

import com.github.kelvimSaidel.domain.repository.SessaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class atualizaSessaoRepository {


    @Autowired

    private SessaoRepository sessaoRepository;

    private Logger logger = LoggerFactory.getLogger(LoggerFactory.class);


    @Scheduled(fixedDelay = 60000)
    public void atualizaStatusSessao(){
         logger.info("Entrou em atualizaStatusSessao");
         sessaoRepository.atualizaStatusSessao();

    }


}

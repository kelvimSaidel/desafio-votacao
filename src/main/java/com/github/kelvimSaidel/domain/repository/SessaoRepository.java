package com.github.kelvimSaidel.domain.repository;

import com.github.kelvimSaidel.domain.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {


    @Query(value = "select * from sessao where id_pauta  = :id_pauta",nativeQuery = true)
    Sessao retornaSessao(@Param("id_pauta") Integer id_pauta);

    @Query(value="update Sessao u set u.status = 'FECHADA' where u.status = \"ABERTA\"" +
            " and dt_fechamento < date_format(SYSDATE(),'%d/%m/%Y %H:%i:%s') ;\n",nativeQuery = true)
    @Transactional
    @Modifying
    void atualizaStatusSessao();

}

package com.github.kelvimSaidel.domain.repository;

import com.github.kelvimSaidel.domain.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {


    @Query(value = "select * from sessao where id_pauta  = :id_pauta",nativeQuery = true)
  // @Query(value = "select c from Sessao c where c.pauta  = :id_pauta")
    Sessao retornaSessao(@Param("id_pauta") Integer id_pauta);

}

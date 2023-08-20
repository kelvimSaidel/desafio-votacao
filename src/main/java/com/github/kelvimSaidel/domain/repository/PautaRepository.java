package com.github.kelvimSaidel.domain.repository;

import com.github.kelvimSaidel.domain.entity.Pauta;
import com.github.kelvimSaidel.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PautaRepository extends JpaRepository<Pauta,Integer> {

    @Query(value = "select * from Pauta c where c.id_pauta = :id",nativeQuery = true)
    Pauta retornarPautas(@Param("id") Integer id);
}

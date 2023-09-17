package com.github.kelvimSaidel.domain.repository;
import com.github.kelvimSaidel.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query(value = "select * from Pauta c where c.usuario = :id",nativeQuery = true)
    Usuario retornarPautasPorUsuario (@Param("id") Integer id);







    }

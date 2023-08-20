package com.github.kelvimSaidel.domain.repository;

import com.github.kelvimSaidel.domain.entity.RegistroVotosUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistroUsuarioRepository extends JpaRepository<RegistroVotosUsuarios,Integer> {

    @Query(value = "select count(id_usuario) from Registro_Votos_Usuarios where id_usuario= :id_usuario and id_pauta = :id_pauta", nativeQuery = true)
    Integer  validaUsuarioJavotou(@Param("id_usuario") Integer id_usuario, @Param("id_pauta") Integer id_pauta);
}

package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping(value = "/Usuario")
public class UsuarioController {

      @Autowired
      private UsuarioRepository usuarioRepository;

      private static String UsuarioNaoEncontrado = "Nenhum usuario encontrado";
      @RequestMapping(value = "/Usuarios",method= RequestMethod.GET)
      @ResponseStatus(HttpStatus.FOUND)
      public List<Usuario> retornaUsuario(){
          List<Usuario> todosUsuario = usuarioRepository.findAll();
          if (todosUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,UsuarioNaoEncontrado);
          }
          return todosUsuario;
      }

     @RequestMapping(value = "/Usuario/{id}",method= RequestMethod.GET)
     @ResponseStatus(HttpStatus.FOUND)
     public Usuario retornaUsuarioPorId(@PathVariable("id") Integer id){
      return usuarioRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND,UsuarioNaoEncontrado));
    }

    @RequestMapping(value = "/Usuario",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastraUsuario(@RequestBody @Valid Usuario usuario){
//          Long existeCpf;
          Boolean validaCpf = null;
        if ((usuario.getId_usuario() != null)) {
            if (usuarioRepository.existsById(usuario.getId_usuario()))
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario ja registrado");
        }

//        existeCpf = usuarioRepository.findAll().stream().filter( c -> c.getCpf().equals(usuario.getCpf())).count();
//        validaCpf = usuario.validaCpf(usuario.getCpf(),existeCpf);
//        if (!validaCpf) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cpf inválido ou já cadastrado");
//        }

//        usuario.setCpf(usuario.formataCpf(usuario.getCpf()));
        return  usuarioRepository.save(usuario);

    }

    @RequestMapping(value = "/Usuario",method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario atualizaUsuario(@RequestBody @Valid Usuario usuario){
//        Long existeCpf;
//        Boolean validaCpf = null;
//        String validaFormatoCpf ="";
//        existeCpf = usuarioRepository.findAll().stream().filter( c -> c.getCpf().equals(usuario.getCpf())).count();
//        validaCpf = usuario.validaCpf(usuario.getCpf(),existeCpf);
        if (usuario.getId_usuario()==null) {
              throw new ResponseStatusException(HttpStatus.NOT_FOUND,"É necessario inserir um ID para alteração de cadastro.");
        }
        if (usuario.getId_usuario()!= null) {
              if (!usuarioRepository.existsById(usuario.getId_usuario())) {
                  throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario invalido, insira uma id cadastrado");
              }

        }
//        if (!validaCpf) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cpf inválido ou já cadastrado");
//        }
//        usuario.setCpf(usuario.formataCpf(usuario.getCpf()));
        return usuarioRepository.save(usuario);
    }

    @RequestMapping(value ="/Usuario/{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable("id") Integer id){
       if (usuarioRepository.findById(id).isEmpty() ) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,UsuarioNaoEncontrado);
        }
          usuarioRepository.deleteById(id);
    }

}

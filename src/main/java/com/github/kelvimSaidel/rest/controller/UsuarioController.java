package com.github.kelvimSaidel.rest.controller;


import com.github.kelvimSaidel.domain.entity.Usuario;
import com.github.kelvimSaidel.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/Usuario")
public class UsuarioController {

      @Autowired
      private UsuarioRepository usuarioRepository;
      @RequestMapping(value = "/UsuariosCadastrados",method= RequestMethod.GET)
      @ResponseStatus(HttpStatus.FOUND)
      public List<Usuario> retornaUsuario(){
          List<Usuario> todosUsuario = usuarioRepository.findAll();
          if (todosUsuario.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum usuario encontrado");
          }
          return todosUsuario;
      }

     @RequestMapping(value = "{id}",method= RequestMethod.GET)
     @ResponseStatus(HttpStatus.FOUND)
     public Usuario retornaUsuarioPorId(@PathVariable("id") Integer id){
        return usuarioRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

//    @RequestMapping(value = "{Nome}",method= RequestMethod.GET)
//    @ResponseStatus(HttpStatus.FOUND)
//    public Usuario retornaUsuarioPorNome(@PathVariable("Nome") String nome){
//         ExampleMatcher  exampleMatcher =
//        return usuarioRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @RequestMapping(method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastraUsuario(@RequestBody Usuario usuario){
        return  usuarioRepository.save(usuario);

    }

    @RequestMapping(method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario atualizaUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @RequestMapping(value ="{id}",method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuario(@PathVariable("id") Integer id){
       if (usuarioRepository.findById(id).isEmpty() ) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
          usuarioRepository.deleteById(id);
    }



//    @RequestMapping(method= RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Usuario atualizaUsuario(@RequestBody String nome, Integer id, Long cpf){
//        return usuarioRepository.atualizaUsuario(id,nome,cpf);
//    }


}

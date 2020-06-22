package br.com.playtomate.usuario.controller;
import br.com.playtomate.usuario.domain.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ControllerUsuario {

    Logger logger = LoggerFactory.getLogger("ControllerUsuario");

    private Mapper mapper;
    public ControllerUsuario(Mapper mapper){
        this.mapper = mapper;
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = mapper.toPessoa(usuarioDTO);
        logger.info("usuario DTO mapeado");
        String idPessoa = usuario.cadastroPessoa();
        logger.info("novo usuario cadastrado com sucesso !");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idPessoa).toUri();
        return ResponseEntity.created(uri).body(idPessoa);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarUsuario(@PathVariable String id){
        Usuario usuario = mapper.buildPessoa();
        usuario.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}

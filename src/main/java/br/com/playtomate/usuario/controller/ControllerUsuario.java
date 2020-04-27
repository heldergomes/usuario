package br.com.playtomate.usuario.controller;

import br.com.playtomate.usuario.domain.TipoPessoa;
import br.com.playtomate.usuario.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ControllerUsuario {

    private Mapper mapper;
    public ControllerUsuario(Mapper mapper){
        this.mapper = mapper;
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = mapper.toPessoa(usuarioDTO);
        if(usuario.getTipoPessoa() == TipoPessoa.JURIDICA)
            mapper.toJuridica(usuario, usuarioDTO);
        String idPessoa = usuario.cadastroPessoa();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(idPessoa).toUri();
        return ResponseEntity.created(uri).body(idPessoa);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarUsuario(@PathVariable String id){
        Usuario usuario = mapper.buildPessoa();
        usuario.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UsuarioDTO> consultarUsuario(@PathVariable String id){
        Usuario usuario = mapper.buildPessoa();
        UsuarioDTO dto = mapper.toDto(usuario.consultarPessoa(id));
        return ResponseEntity.ok().body(dto);
    }
}

package br.com.playtomate.usuario.controller;

import br.com.playtomate.usuario.domain.TipoPessoa;
import br.com.playtomate.usuario.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ControllerPessoa {

    private Mapper mapper;
    public ControllerPessoa(Mapper mapper){
        this.mapper = mapper;
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody PessoaDTO pessoaDTO){
        Usuario usuario = mapper.toPessoa(pessoaDTO);
        if(usuario.getTipoPessoa() == TipoPessoa.JURIDICA)
            mapper.toJuridica(usuario, pessoaDTO);
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
    public ResponseEntity<PessoaDTO> consultarUsuario(@PathVariable String id){
        Usuario usuario = mapper.buildPessoa();
        PessoaDTO dto = mapper.toDto(usuario.consultarPessoa(id));
        return ResponseEntity.ok().body(dto);
    }
}

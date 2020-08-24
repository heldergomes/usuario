package br.com.playtomate.usuario.controller.usuario;
import br.com.playtomate.usuario.controller.security.JwtAuthenticationFilter;
import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
        return ResponseEntity.created(uri).body("{\"id_usuario\": \"" + idPessoa + "\" }");
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletarUsuario(@PathVariable String id){
        Usuario usuario = mapper.buildPessoa();
        usuario.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable String id){
        Usuario usuario = mapper.buildPessoa();
        logger.info("usuario DTO mapeado");
        usuario = usuario.buscarUsuario(id);
        logger.info("usuario consultado com sucesso !");
        UsuarioDTO dto = mapper.toDto(usuario);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> buscarUsuarios(){
        Usuario usuario = mapper.buildPessoa();
        logger.info("usuario DTO mapeado");
        List<Usuario> usuarios = usuario.buscarTodosUsuarios();
        logger.info("busca de todos os usuarios realizada com sucesso !");
        List<UsuarioDTO>  dto = mapper.toDto(usuarios);
        return ResponseEntity.ok(dto);
    }
}

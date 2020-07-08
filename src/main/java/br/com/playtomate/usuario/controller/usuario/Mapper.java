package br.com.playtomate.usuario.controller.usuario;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.usuario.Perfil;
import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class Mapper {

    Logger logger = LoggerFactory.getLogger("Mapper");

    private BCryptPasswordEncoder crypt;
    private ServiceUsuario serviceUsuario;
    public Mapper(ServiceUsuario serviceUsuario, BCryptPasswordEncoder crypt) {
        this.serviceUsuario = serviceUsuario;
        this.crypt = crypt;
    }

    public Usuario toPessoa(UsuarioDTO dto){
        Usuario usuario = Usuario.builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .senha(crypt.encode(dto.getSenha()))
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .serviceUsuario(serviceUsuario)
                .build();

        usuario.getPerfils().add(Perfil.CLIENTE.getCodigo());
        usuario.getPerfils().add(Perfil.ADMIN.getCodigo());
        logger.info("Usuario mapeado: " + usuario.toString());
        return usuario;
    }

    public Usuario buildPessoa(){
        Usuario usuario = Usuario.builder()
                .serviceUsuario(serviceUsuario)
                .build();
        return usuario;
    }

    public UsuarioDTO toDto(Usuario usuario) {
        return UsuarioDTO.builder()
                .login(usuario.getLogin())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .build();
    }
}
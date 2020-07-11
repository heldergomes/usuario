package br.com.playtomate.usuario.controller.usuario;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.usuario.Perfil;
import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
                .perfils(new ArrayList<>())
                .serviceUsuario(serviceUsuario)
                .logger(LoggerFactory.getLogger("Usuario"))
                .build();
        dto.getPerfils().stream()
                .forEach(perfil -> usuario.getPerfils().add(Perfil.toEnum(perfil).getCodigo()));

        logger.info("Usuario mapeado: " + usuario.toString());
        return usuario;
    }

    public Usuario buildPessoa(){
        Usuario usuario = Usuario.builder()
                .serviceUsuario(serviceUsuario)
                .logger(LoggerFactory.getLogger("Usuario"))
                .build();
        logger.info("Usuario mapeado: " + usuario.toString());
        return usuario;
    }

    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = UsuarioDTO.builder()
                .login(usuario.getLogin())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .build();
        logger.info("Usuario DTO mapeado: " + dto.toString());
        return dto;
    }

    public List<UsuarioDTO> toDto(List<Usuario> usuarios) {
         List<UsuarioDTO> dtos =
                 usuarios.stream()
                    .map(usuario -> UsuarioDTO.builder()
                        .login(usuario.getLogin())
                        .email(usuario.getEmail())
                        .nome(usuario.getNome())
                        .telefone(usuario.getTelefone())
                        .build())
                    .collect(Collectors.toList());
         logger.info("Lista de Usuario DTO mapeado !");
         return dtos;
    }
}
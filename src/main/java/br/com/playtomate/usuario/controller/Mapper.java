package br.com.playtomate.usuario.controller;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        logger.info("Usuario mapeado: " + usuario.toString());
        return usuario;
    }

    public Usuario buildPessoa(){
        Usuario usuario = Usuario.builder().build();
        usuario.setServiceUsuario(serviceUsuario);
        return usuario;
    }

}
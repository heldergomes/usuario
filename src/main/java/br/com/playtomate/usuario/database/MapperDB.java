package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperDB {

    public UsuarioModel toModel(Usuario usuario) {
        return UsuarioModel.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .nome(usuario.getNome())
                .senha(usuario.getSenha())
                .telefone(usuario.getTelefone())
                .perfils(usuario.getPerfils())
                .build();
    }

    public Usuario toUsuario(UsuarioModel model) {
        return Usuario.builder()
                .id(model.getId())
                .email(model.getEmail())
                .login(model.getLogin())
                .nome(model.getNome())
                .senha(model.getSenha())
                .telefone(model.getTelefone())
                .perfils(model.getPerfils())
                .build();
    }

    public List<Usuario> toUsuario(List<UsuarioModel> models) {
        List<Usuario> usuarios = models
                .stream()
                .map(model -> Usuario.builder()
                                .id(model.getId())
                                .email(model.getEmail())
                                .login(model.getLogin())
                                .nome(model.getNome())
                                .senha(model.getSenha())
                                .telefone(model.getTelefone())
                                .perfils(model.getPerfils())
                                .build()
                ).collect(Collectors.toList());
        return usuarios;
    }
}

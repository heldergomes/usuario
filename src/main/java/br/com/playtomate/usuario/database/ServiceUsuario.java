package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceUsuario {

    private RepositoryUsuario repository;
    public ServiceUsuario(RepositoryUsuario repository){
        this.repository = repository;
    }

    public void salvar(Usuario usuario){
        repository.save(usuario);
    }

    public void deletar(String id){
        repository.findById(id);
        repository.deleteById(id);
    }

    public Usuario consultar(String id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if(usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }else {
            throw new RuntimeException();
        }
    }
}

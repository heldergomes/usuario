package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryUsuario extends MongoRepository<UsuarioModel, String> {

    @Query("{ 'login' : '?0' }")
    Optional<UsuarioModel> findByLogin(String login);

}

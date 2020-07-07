package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuario extends MongoRepository<Usuario, String> {

    @Query("{ 'login' : '?0' }")
    Usuario findByLogin(String login);

}

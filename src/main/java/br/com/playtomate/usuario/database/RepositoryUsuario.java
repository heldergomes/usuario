package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUsuario extends MongoRepository<Usuario, String> {

}

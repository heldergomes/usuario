package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {

    Logger logger = LoggerFactory.getLogger("ServiceUsuario");

    private RepositoryUsuario repository;
    public ServiceUsuario(RepositoryUsuario repository){
        this.repository = repository;
    }

    public void salvar(Usuario usuario) {
        try {
            repository.save(usuario);
            logger.info("usuario cadastrado no banco de dados");
        } catch (DuplicateKeyException ex) {
            logger.error("usuario com chave duplicada: " + ex.getMessage());
            throw new ChaveDuplicadaException(ex.getMessage());
        }
    }

    public void deletar(String id){
        repository.findById(id);
        repository.deleteById(id);
    }
}

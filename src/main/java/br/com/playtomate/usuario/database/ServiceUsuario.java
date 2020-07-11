package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;
import java.util.List;


@Service
public class ServiceUsuario {

    Logger logger = LoggerFactory.getLogger("ServiceUsuario");

    @Autowired
    RepositoryUsuario repository;
    @Autowired
    MapperDB mapper;

    public void salvar(Usuario usuario) {
        try {
            repository.save(mapper.toModel(usuario));
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

    public Usuario buscarPorLogin(String login){
        UsuarioModel model =  repository.findByLogin(login).orElseThrow(InvalidKeyException::new);
        logger.info("usuario encontrado com sucesso atraves do login!");
        return mapper.toUsuario(model);
    }

    public Usuario buscarPorId(String id) {
        UsuarioModel model = repository.findById(id).orElseThrow(InvalidKeyException::new);
        logger.info("usuario encontrado com sucesso atraves do id!");
        return mapper.toUsuario(model);
    }

    public List<Usuario> buscarTodos() {
        List<UsuarioModel> models = repository.findAll();
        logger.info("busca de todos usuarios realizada com sucesso !");
        return mapper.toUsuario(models);
    }
}

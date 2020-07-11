package br.com.playtomate.usuario.database;

import br.com.playtomate.usuario.controller.security.CredenciaisDTO;
import br.com.playtomate.usuario.controller.usuario.MapperDB;
import br.com.playtomate.usuario.domain.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.management.openmbean.InvalidKeyException;


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
        return mapper.toUsuario(model);
    }

    public Usuario buscarPorId(String id) {
        UsuarioModel model = repository.findById(id).orElseThrow(InvalidKeyException::new);
        return mapper.toUsuario(model);
    }
}

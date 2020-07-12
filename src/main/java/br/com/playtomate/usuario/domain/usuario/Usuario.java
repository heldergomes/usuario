package br.com.playtomate.usuario.domain.usuario;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.security.Autenticador;
import br.com.playtomate.usuario.domain.security.UsuarioSecurity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
public class Usuario {

    private String id;
    private String login;
    private String email;
    @Setter
    private String senha;
    private String nome;
    private int telefone;
    private List<Integer> perfils;

    private Logger logger;
    private ServiceUsuario serviceUsuario;

    public String cadastroPessoa(){
        id = String.valueOf(UUID.randomUUID());
        logger.info("Geração do usuario gerada com sucesso !");
        serviceUsuario.salvar(this);
        logger.info("usuario salvo com sucesso !");
        return id;
    }

    public void deletarPessoa(String id){
        UsuarioSecurity usuarioSecurity = Autenticador.autenticarUsuario();
        if (usuarioSecurity == null || !usuarioSecurity.hasRole(Perfil.ADMIN) && !id.equals(usuarioSecurity.getId())) {
            logger.error("usuario negado devido falta de acesso !");
            throw new AutorizacaoException("Acesso Negado !");
        }
        serviceUsuario.deletar(id);
    }

    public Set<Perfil> buscarPerfils(){
        return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone=" + telefone +
                ", perfil=" + perfils +
                '}';
    }

    public Usuario buscarUsuario(String id) {
        UsuarioSecurity usuarioSecurity = Autenticador.autenticarUsuario();
        if (usuarioSecurity == null || !usuarioSecurity.hasRole(Perfil.ADMIN) && !id.equals(usuarioSecurity.getId())) {
            logger.error("usuario negado devido falta de acesso !");
            throw new AutorizacaoException("Acesso Negado !");
        }
        logger.info("usuario com acesso permitido !");
        return serviceUsuario.buscarPorId(id);
    }

    public List<Usuario> buscarTodosUsuarios() {
        return serviceUsuario.buscarTodos();
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return serviceUsuario.buscarPorEmail(email);
    }

    public void atualizar() {
        serviceUsuario.atualizarUsuario(this);
    }
}

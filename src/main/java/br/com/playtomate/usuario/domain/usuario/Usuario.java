package br.com.playtomate.usuario.domain.usuario;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.security.Autenticador;
import br.com.playtomate.usuario.domain.security.UsuarioSecurity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String senha;
    private String nome;
    private int telefone;
    private List<Integer> perfils;

    private ServiceUsuario serviceUsuario;

    public String cadastroPessoa(){
        id = String.valueOf(UUID.randomUUID());
        serviceUsuario.salvar(this);
        return id;
    }

    public void deletarPessoa(String id){
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
        if (usuarioSecurity == null || !usuarioSecurity.hasRole(Perfil.ADMIN) && !id.equals(usuarioSecurity.getId()))
            throw new AutorizacaoException("Acesso Negado !");
        return serviceUsuario.buscarPorId(id);
    }
}

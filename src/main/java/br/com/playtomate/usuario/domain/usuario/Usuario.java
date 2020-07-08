package br.com.playtomate.usuario.domain.usuario;

import br.com.playtomate.usuario.database.ServiceUsuario;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@Document
public class Usuario {

    @Id
    private String id;
    private String login;
    private String email;
    private String senha;
    private String nome;
    private int telefone;
    private Set<Integer> perfils = new HashSet<>();

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
        return serviceUsuario.buscarPorId(id);
    }
}

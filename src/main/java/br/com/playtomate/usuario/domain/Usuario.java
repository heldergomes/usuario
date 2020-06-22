package br.com.playtomate.usuario.domain;

import br.com.playtomate.usuario.database.ServiceUsuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Getter
@Setter
@Document
public class Usuario {

    @Id
    private String id;
    private String login;
    private String email;
    private String senha;
    private String nome;
    private int telefone;
    private Juridica pessoaJuridica;
    private Localidade localidade;

    @Autowired
    private ServiceUsuario serviceUsuario;

    public String cadastroPessoa(){
        setId(String.valueOf(UUID.randomUUID()));
        serviceUsuario.salvar(this);
        return id;
    }

    public void deletarPessoa(String id){
        serviceUsuario.deletar(id);
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
                ", pessoaJuridica=" + pessoaJuridica +
                ", localidade=" + localidade +
                '}';
    }
}

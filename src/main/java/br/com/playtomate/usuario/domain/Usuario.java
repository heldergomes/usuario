package br.com.playtomate.usuario.domain;

import br.com.playtomate.usuario.database.ServiceUsuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Usuario {

    @Id
    private String id;
    private TipoPessoa tipoPessoa;
    private String login;
    private String email;
    private String senha;
    private String nome;
    private int numeroTelefone;
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

    public Usuario consultarPessoa(String id){
        return serviceUsuario.consultar(id);
    }

    public Usuario buildPessoa(String login,
                               String email,
                               String senha,
                               String nome,
                               int numeroTelefone,
                               int tipoPessoa){
        this.setLogin(login);
        this.setEmail(email);
        this.setSenha(senha);
        this.setNome(nome);
        this.setNumeroTelefone(numeroTelefone);
        this.setTipoPessoa(TipoPessoa.converterTipoPessoa(tipoPessoa));
        return this;
    }

    public void buildPessoaJuridica(String nomeEmpresa){
        this.setPessoaJuridica(new Juridica(nomeEmpresa));
    }

    public void buildLocalidade(String endereco,
                                String cidade,
                                String estado,
                                String codigoPostal){
        this.setLocalidade(new Localidade(endereco, cidade, estado, codigoPostal));
    }
}

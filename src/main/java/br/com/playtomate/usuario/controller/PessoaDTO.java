package br.com.playtomate.usuario.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class PessoaDTO {

    private int tipoPessoa;
    private String login;
    private String email;
    private String senha;
    private String nome;
    private int numeroTelefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String codigoPostal;
    private String nomeEmpresa;

}

package br.com.playtomate.usuario.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioDTO {

    @NotNull(message = "O campo tipoPessoa não pode ser nulo")
    private int tipoPessoa;
    @NotNull(message = "O campo login não pode ser nulo")
    @NotEmpty(message = "O campo login não pode estar vazio")
    private String login;
    @NotNull(message = "O campo email não pode ser nulo")
    @NotEmpty(message = "O campo email não pode estar vazio")
    private String email;
    @NotNull(message = "O campo senha não pode ser nulo")
    @NotEmpty(message = "O campo senha não pode estar vazio")
    private String senha;
    @NotNull(message = "O campo nome não pode ser nulo")
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;
    @NotNull(message = "O campo numeroTelefone não pode ser nulo")
    private int numeroTelefone;
    @NotNull(message = "O campo endereco não pode ser nulo")
    @NotEmpty(message = "O campo endereco não pode estar vazio")
    private String endereco;
    @NotNull(message = "O campo cidade não pode ser nulo")
    @NotEmpty(message = "O campo cidade não pode estar vazio")
    private String cidade;
    @NotNull(message = "O campo estado não pode ser nulo")
    @NotEmpty(message = "O campo estado não pode estar vazio")
    private String estado;
    @NotNull(message = "O campo codigoPostal não pode ser nulo")
    @NotEmpty(message = "O campo codigoPostal não pode estar vazio")
    private String codigoPostal;
    private String nomeEmpresa;

}

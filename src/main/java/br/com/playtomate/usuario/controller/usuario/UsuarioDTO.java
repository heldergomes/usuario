package br.com.playtomate.usuario.controller.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {

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
    private int telefone;
}

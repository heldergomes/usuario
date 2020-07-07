package br.com.playtomate.usuario.controller.security;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CredenciaisDTO {

    @NotNull(message = "O campo login não pode ser nulo")
    @NotEmpty(message = "O campo login não pode estar vazio")
    private String login;
    @NotNull(message = "O campo senha não pode ser nulo")
    @NotEmpty(message = "O campo senha não pode estar vazio")
    private String senha;

}

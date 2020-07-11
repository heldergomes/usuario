package br.com.playtomate.usuario.domain.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class Autenticador {

    public static UsuarioSecurity autenticarUsuario(){
        try {
            return (UsuarioSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }
}

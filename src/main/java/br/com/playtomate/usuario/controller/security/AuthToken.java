package br.com.playtomate.usuario.controller.security;

import br.com.playtomate.usuario.domain.security.Autenticador;
import br.com.playtomate.usuario.domain.security.JwtUtil;
import br.com.playtomate.usuario.domain.security.UsuarioSecurity;
import br.com.playtomate.usuario.domain.usuario.AutenticadorSenha;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/auth")
public class AuthToken {

    private JwtUtil jwtUtil;
    private AutenticadorSenha autenticadorSenha;
    public AuthToken(JwtUtil jwtUtil, AutenticadorSenha autenticadorSenha){
        this.jwtUtil = jwtUtil;
        this.autenticadorSenha = autenticadorSenha;
    }

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UsuarioSecurity usuarioSecurity = Autenticador.autenticarUsuario();
        String token = jwtUtil.generateToken(usuarioSecurity.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ResponseEntity<Void> forgot(@Valid @RequestBody @NotNull String email){
        autenticadorSenha.enviarNovaSenha(email);
        return ResponseEntity.noContent().build();
    }

}

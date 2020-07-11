package br.com.playtomate.usuario.controller.security;

import br.com.playtomate.usuario.domain.security.Autenticador;
import br.com.playtomate.usuario.domain.security.JwtUtil;
import br.com.playtomate.usuario.domain.security.UsuarioSecurity;
import br.com.playtomate.usuario.domain.security.UsuarioServiceSecurity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthToken {

    private JwtUtil jwtUtil;
    public AuthToken(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UsuarioSecurity usuarioSecurity = Autenticador.autenticarUsuario();
        String token = jwtUtil.generateToken(usuarioSecurity.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }
}

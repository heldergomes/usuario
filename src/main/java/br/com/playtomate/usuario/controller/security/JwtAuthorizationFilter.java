package br.com.playtomate.usuario.controller.security;

import br.com.playtomate.usuario.domain.security.JwtUtil;
import br.com.playtomate.usuario.domain.security.UsuarioSecurity;
import br.com.playtomate.usuario.domain.security.UsuarioServiceSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtil jwtUtil;
    private UsuarioServiceSecurity usuarioServiceSecurity;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtUtil jwtUtil,
                                  UsuarioServiceSecurity usuarioServiceSecurity) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.usuarioServiceSecurity = usuarioServiceSecurity;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken auth = getAuthentication(authorization.substring(7));
            if (auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.tokenValido(token)){
            String login = jwtUtil.getLogin(token);
            UserDetails usuarioSecurity = usuarioServiceSecurity.loadUserByUsername(login);
            return new UsernamePasswordAuthenticationToken(usuarioSecurity, null, usuarioSecurity.getAuthorities());
        }
        return null;
    }
}

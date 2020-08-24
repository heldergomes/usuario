package br.com.playtomate.usuario.controller.security;

import br.com.playtomate.usuario.domain.security.JwtUtil;
import br.com.playtomate.usuario.domain.security.UsuarioSecurity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            CredenciaisDTO dto = new ObjectMapper()
                    .readValue(request.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha(), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(authToken);
            return authentication;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String login = ((UsuarioSecurity) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(login);
        String id = ((UsuarioSecurity) authResult.getPrincipal()).getId();
        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.getWriter()
                .append("{\"" + "id_usuario\": \"" + id +"\", "
                        + "\"token\": \"" + token +"\"" + "}");
    }

    private class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request,
                                            HttpServletResponse response,
                                            AuthenticationException exception) throws IOException, ServletException {
            response.setStatus(401);
            response.getWriter().append(json());
            response.setHeader("Content-Type", "application/json");
        }

        private String json(){
            long date = new Date().getTime();
            return "{\"timestamp\": "+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date) + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Não autorizado\", "
                    + "\"message\": \"Email ou senha inválido\", "
                    + "\"path\": \"/login\"}";
        }
    }
}

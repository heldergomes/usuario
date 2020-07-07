package br.com.playtomate.usuario.domain.security;

import br.com.playtomate.usuario.database.ServiceUsuario;
import br.com.playtomate.usuario.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceSecurity implements UserDetailsService {

    @Autowired
    ServiceUsuario serviceUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = serviceUsuario.buscarPorLogin(username);
        if (usuario == null)
            throw new UsernameNotFoundException(username);
        return new UsuarioSecurity(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.buscarPerfils());
    }
}

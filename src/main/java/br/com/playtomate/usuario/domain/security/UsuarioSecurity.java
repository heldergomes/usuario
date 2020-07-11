package br.com.playtomate.usuario.domain.security;

import br.com.playtomate.usuario.domain.usuario.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioSecurity implements UserDetails {

    private String id;
    private String login;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioSecurity(String id, String login, String senha, Set<Perfil> perfils){
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.authorities =
                perfils.stream().map(
                        x -> new SimpleGrantedAuthority(x.getDescricao())
                ).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Perfil perfil) {
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }
}

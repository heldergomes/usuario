package br.com.playtomate.usuario.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticadorSenha {

    @Autowired
    BCryptPasswordEncoder crypt;

    @Autowired
    EmailService emailService;

    public void enviarNovaSenha(String email){
        Usuario usuario = Usuario.builder().build().buscarUsuarioPorEmail(email);
        String novaSenha = new GeradorSenhaImpl().gerador();
        usuario.setSenha(crypt.encode(novaSenha));
        usuario.atualizar();
        emailService.sendEmailEsqueciSenhaHtml(usuario, novaSenha);

    }
}

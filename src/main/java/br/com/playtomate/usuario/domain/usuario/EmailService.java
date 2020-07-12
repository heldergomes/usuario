package br.com.playtomate.usuario.domain.usuario;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendEmailEsqueciSenhaText(Usuario usuario, String novaSenha);

    void sendEmailText(SimpleMailMessage mail);

    void sendEmailEsqueciSenhaHtml(Usuario usuario, String novaSenha);

    void sendEmailHtml(MimeMessage mail);
}

package br.com.playtomate.usuario.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class GmailService extends EmailAbstractService{

    @Autowired
    MailSender mailSender;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendEmailText(SimpleMailMessage mail) {
        mailSender.send(mail);
    }

    @Override
    public void sendEmailHtml(MimeMessage mail) {
        javaMailSender.send(mail);
    }
}

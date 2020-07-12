package br.com.playtomate.usuario.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class EmailAbstractService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine template;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailEsqueciSenhaText(Usuario usuario, String novaSenha) {
        SimpleMailMessage mailService = prepararEmailEsqueciSenhaText(usuario, novaSenha);
        sendEmailText(mailService);
    }

    protected SimpleMailMessage prepararEmailEsqueciSenhaText(Usuario usuario, String novaSenha) {
        SimpleMailMessage emailService = new SimpleMailMessage();
        emailService.setTo(usuario.getEmail());
        emailService.setFrom(sender);
        emailService.setSubject("Playtomate - Esqueci minha senha !");
        emailService.setSentDate(new Date(System.currentTimeMillis()));
        emailService.setText("Olá " + usuario.getNome());
        emailService.setText("sua nova senha é: " + usuario.getSenha());
        emailService.setText("Por favor troque essa senha gerada por uma nova !!!");
        return emailService;
    }

    @Override
    public void sendEmailEsqueciSenhaHtml(Usuario usuario, String novaSenha) {
        try {
            MimeMessage mailService = prepararEmailEsqueciSenhaHtml(usuario, novaSenha);
            sendEmailHtml(mailService);
        } catch (MessagingException ex) {
            sendEmailEsqueciSenhaText(usuario, novaSenha);
        }
    }

    protected MimeMessage prepararEmailEsqueciSenhaHtml(Usuario usuario, String novaSenha) throws MessagingException {
        MimeMessage emailService = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(emailService, true);
        helper.setTo(usuario.getEmail());
        helper.setFrom(sender);
        helper.setSubject("Playtomate - Esqueci minha senha !");
        helper.setSentDate(new Date(System.currentTimeMillis()));
        helper.setText(formatarEmail(usuario, novaSenha), true);
        return emailService;
    }

    protected String formatarEmail(Usuario usuario, String novaSenha){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("usuario", usuario);
        variables.put("novaSenha", novaSenha);
        Context context = new Context();
        context.setVariables(variables);
        return template.process("email/esqueciSenha", context);
    }
}

package br.com.playtomate.usuario.config;

import br.com.playtomate.usuario.domain.usuario.EmailService;
import br.com.playtomate.usuario.domain.usuario.GmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorrelationIDInterceptor());
    }

    @Bean
    public EmailService emailService() {
        return new GmailService();
    }

}

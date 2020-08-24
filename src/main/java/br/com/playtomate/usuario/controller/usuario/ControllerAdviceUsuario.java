package br.com.playtomate.usuario.controller.usuario;

import br.com.playtomate.usuario.database.ChaveDuplicadaException;
import br.com.playtomate.usuario.database.ObjetoNaoEncontradoException;
import br.com.playtomate.usuario.database.UsuarioInexistenteException;
import br.com.playtomate.usuario.domain.usuario.AutorizacaoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviceUsuario {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsuarioInexistenteException.class)
    @ResponseBody
    ResponseEntity<ErrorInfo> usuarioInexistenteException(HttpServletRequest req, UsuarioInexistenteException ex){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL()), headers, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChaveDuplicadaException.class)
    @ResponseBody
    ResponseEntity<ErrorInfo> chaveDuplicadaException(HttpServletRequest req, ChaveDuplicadaException ex){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (ex.getMessage().contains("login")){
            return new ResponseEntity<>(new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL(), "login"), headers, HttpStatus.BAD_REQUEST);
        }else if (ex.getMessage().contains("email")){
            return new ResponseEntity<>(new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL(), "email"), headers, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL()), headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AutorizacaoException.class)
    @ResponseBody
    ResponseEntity<ErrorInfo> chaveDuplicadaException(HttpServletRequest req, AutorizacaoException ex){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ErrorInfo(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name() ,ex.getMessage(), req.getRequestURL()), headers, HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    @ResponseBody
    ResponseEntity<ErrorInfo> objetoNaoEncontradoException(HttpServletRequest req, ObjetoNaoEncontradoException ex){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL()), headers, HttpStatus.BAD_REQUEST);
    }

}

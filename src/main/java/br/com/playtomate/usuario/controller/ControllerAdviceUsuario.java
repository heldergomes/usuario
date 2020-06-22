package br.com.playtomate.usuario.controller;

import br.com.playtomate.usuario.database.ChaveDuplicadaException;
import br.com.playtomate.usuario.database.UsuarioInexistenteException;
import org.springframework.http.HttpStatus;
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
    ErrorInfo usuarioInexistenteException(HttpServletRequest req, UsuarioInexistenteException ex){
        return new ErrorInfo(req.getRequestURL(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChaveDuplicadaException.class)
    @ResponseBody
    ErrorInfo chaveDuplicadaException(HttpServletRequest req, ChaveDuplicadaException ex){
        if (ex.getMessage().contains("login")){
            return new ErrorInfo(req.getRequestURL(), ex.getMessage(), "login");
        }else if (ex.getMessage().contains("email")){
            return new ErrorInfo(req.getRequestURL(), ex.getMessage(), "email");
        }else{
            return new ErrorInfo(req.getRequestURL(), ex.getMessage());
        }
    }
}

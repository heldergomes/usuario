package br.com.playtomate.usuario.controller;

import br.com.playtomate.usuario.database.UsuarioInexistenteException;
import br.com.playtomate.usuario.domain.TipoPessoaException;
import com.mongodb.MongoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(MongoException.class)
    @ResponseBody
    ErrorInfo mongoException(HttpServletRequest req, MongoException ex){
        return new ErrorInfo(req.getRequestURL(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TipoPessoaException.class)
    @ResponseBody
    ErrorInfo tipoPessoaException(HttpServletRequest req, TipoPessoaException ex){
        return new ErrorInfo(req.getRequestURL(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErrorInfo methodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex){
        return new ErrorInfo(req.getRequestURL(), ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}

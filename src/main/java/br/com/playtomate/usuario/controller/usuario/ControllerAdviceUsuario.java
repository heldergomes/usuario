package br.com.playtomate.usuario.controller.usuario;

import br.com.playtomate.usuario.database.ChaveDuplicadaException;
import br.com.playtomate.usuario.database.ObjetoNaoEncontradoException;
import br.com.playtomate.usuario.database.UsuarioInexistenteException;
import br.com.playtomate.usuario.domain.usuario.AutorizacaoException;
import com.mongodb.MongoSocketException;
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
        return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChaveDuplicadaException.class)
    @ResponseBody
    ErrorInfo chaveDuplicadaException(HttpServletRequest req, ChaveDuplicadaException ex){
        if (ex.getMessage().contains("login")){
            return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL(), "login");
        }else if (ex.getMessage().contains("email")){
            return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL(), "email");
        }else{
            return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL());
        }
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AutorizacaoException.class)
    @ResponseBody
    ErrorInfo chaveDuplicadaException(HttpServletRequest req, AutorizacaoException ex){
        return new ErrorInfo(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.name() ,ex.getMessage(), req.getRequestURL());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    @ResponseBody
    ErrorInfo objetoNaoEncontradoException(HttpServletRequest req, ObjetoNaoEncontradoException ex){
        return new ErrorInfo(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name() ,ex.getMessage(), req.getRequestURL());
    }

}

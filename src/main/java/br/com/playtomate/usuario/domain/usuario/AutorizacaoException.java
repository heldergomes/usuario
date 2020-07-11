package br.com.playtomate.usuario.domain.usuario;

public class AutorizacaoException extends RuntimeException{
    public AutorizacaoException(String message){
        super(message);
    }
}

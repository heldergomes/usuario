package br.com.playtomate.usuario.database;

public class UsuarioInexistenteException extends RuntimeException {

    public UsuarioInexistenteException(String message){
        super(message);
    }
}

package br.com.playtomate.usuario.database;

public class ChaveDuplicadaException extends RuntimeException{

    public ChaveDuplicadaException(String message) {
       super(message);
    }
}

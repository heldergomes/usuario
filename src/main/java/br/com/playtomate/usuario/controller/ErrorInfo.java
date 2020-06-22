package br.com.playtomate.usuario.controller;

public class ErrorInfo {

    public StringBuffer url;
    public String message;
    public String campo_invalido;

    public ErrorInfo(StringBuffer url, String message, String campoInvalido) {
        this.url = url;
        this.message = message;
        this.campo_invalido = campoInvalido;
    }

    public ErrorInfo(StringBuffer url, String message) {
        this.url = url;
        this.message = message;
    }
}

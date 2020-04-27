package br.com.playtomate.usuario.controller;

public class ErrorInfo {

    public final StringBuffer url;
    public final String message;

    public ErrorInfo(StringBuffer url, String message) {
        this.url = url;
        this.message = message;
    }

}

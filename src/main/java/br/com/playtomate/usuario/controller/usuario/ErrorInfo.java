package br.com.playtomate.usuario.controller.usuario;

public class ErrorInfo {


    public Integer httpStatus;
    public String error;
    public String message;
    public StringBuffer url;
    public String campo_invalido;

    public ErrorInfo(StringBuffer url, Integer httpStatus, String message, String error, String campoInvalido) {
        this.url = url;
        this.httpStatus = httpStatus;
        this.message = message;
        this.error = error;
        this.campo_invalido = campoInvalido;
    }

    public ErrorInfo(StringBuffer url, Integer httpStatus, String error, String message) {
        this.url = url;
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
    }
}

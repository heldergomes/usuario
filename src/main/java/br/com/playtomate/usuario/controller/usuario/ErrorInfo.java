package br.com.playtomate.usuario.controller.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorInfo {

    public Integer httpStatus;
    public String statusName;
    public String message;
    public StringBuffer url;
    public String campo_invalido;

    public ErrorInfo(Integer httpStatus, String statusName, String message, StringBuffer url, String campoInvalido) {
        this.httpStatus = httpStatus;
        this.statusName = statusName;
        this.message = message;
        this.url = url;
        this.campo_invalido = campoInvalido;
    }

    public ErrorInfo(Integer httpStatus, String statusName, String message, StringBuffer url) {
        this.httpStatus = httpStatus;
        this.statusName = statusName;
        this.message = message;
        this.url = url;
    }
}

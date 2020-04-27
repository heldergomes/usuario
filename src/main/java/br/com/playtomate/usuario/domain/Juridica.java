package br.com.playtomate.usuario.domain;

import lombok.Getter;

@Getter
public class Juridica {

    private String nomeEmpresa;

    public Juridica(String nomeEmpresa){
        this.nomeEmpresa = nomeEmpresa;
    }

}

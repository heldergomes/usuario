package br.com.playtomate.usuario.domain;

import lombok.Getter;

@Getter
public class Localidade {

    private String endereco;
    private String cidade;
    private String estado;
    private String codigoPostal;

    public Localidade(String endereco,
                      String cidade,
                      String estado,
                      String codigoPostal){
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
    }
}

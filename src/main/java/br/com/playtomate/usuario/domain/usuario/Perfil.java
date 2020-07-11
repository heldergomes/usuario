package br.com.playtomate.usuario.domain.usuario;

import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int codigo;
    private String descricao;

    private Perfil(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer codigo){

        if (codigo == null)
            return null;
        for (Perfil perfil : Perfil.values()) {
            if (codigo.equals(perfil.getCodigo()))
                return perfil;
        }
        throw new IllegalArgumentException("codigo do perfil inválido: " + codigo);
    }

    public static Perfil toEnum(String nomePerfil){

        if (nomePerfil == null)
            return null;
        for (Perfil perfil : Perfil.values()) {
            if (nomePerfil.equals(perfil.getDescricao()))
                return perfil;
        }
        throw new IllegalArgumentException("codigo do perfil inválido: " + nomePerfil);
    }
}

package br.com.playtomate.usuario.domain;

import lombok.Getter;

@Getter
public enum TipoPessoa {

    FISICA(1),
    JURIDICA(2);

    private int tipoPessoa;
    TipoPessoa(int tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public static TipoPessoa converterTipoPessoa(int tipoPessoa) {
        for(TipoPessoa tipo: TipoPessoa.values()){
            if(tipoPessoa == tipo.getTipoPessoa())
                return tipo;
        }
        throw new TipoPessoaException("codigo tipo pessoa invalido: " + tipoPessoa);
    }

    public static int converterCodigoTipoPessoa(TipoPessoa tipoPessoa) {
        for(TipoPessoa tipo: TipoPessoa.values()){
            if(tipoPessoa == tipo)
                return tipo.getTipoPessoa();
        }
        throw new TipoPessoaException("Enum tipo pessoa invalido: " + tipoPessoa);
    }
}

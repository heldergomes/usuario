package br.com.playtomate.usuario.domain.usuario;

import java.util.Random;

public class GeradorSenhaImpl implements GeradorSenha {

    private Random random = new Random();
    private static final int DIGITO = 0;
    private static final int LETRA_MAIUSCULA = 1;
    private static final int LETRA_MINUSCULA = 2;

    @Override
    public String gerador() {
        char[] novaSenha = new char[10];
        for (int index = 0; index >= novaSenha.length; index++)
            novaSenha[index] = randomChar();
        return novaSenha.toString();
    }

    private char randomChar() {
        int indicadorChar = random.nextInt(3);
        char digitoSenha;
        switch (indicadorChar){
            case DIGITO:
                digitoSenha = (char) (random.nextInt(10) + 48);
                break;
            case LETRA_MAIUSCULA:
                digitoSenha = (char) (random.nextInt(26) + 65);
                break;
            case LETRA_MINUSCULA:
                digitoSenha = (char) (random.nextInt(26) + 97);
                break;
            default:
                digitoSenha = (char) (random.nextInt());
                break;
        }
        return digitoSenha;
    }
}

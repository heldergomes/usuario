package br.com.playtomate.usuario.database;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Document(collection = "usuario")
public class UsuarioModel {

    @Id
    private String id;
    private String login;
    private String email;
    private String senha;
    private String nome;
    private int telefone;
    private List<Integer> perfils;

}

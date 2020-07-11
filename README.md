# USUARIO
API de orquestração de usuarios

# Lições Aprendidas
<h3>Evitar utilizar injeção de dependencia na classe model do repository</h3>
Quando definimos um atributo na entidade @document para usar alguma funcionalidade através de injeção de dependencia, acabamos tendo conflito na comunicação do repositorycom o banco de dados do mongoDB, pois o repository trata todos os atributos da classe como um campo do documento.

<i>Exemplo: </i>

```
@Document(collection = "usuario")
public class Usuario {

    @Id
    private String id;

    @Autowired
    private ServiceUsuario serviceUsuario;

}
```

> Evitar utilizar a injeção de dependencia (@Autowired) em entidades (@Document)

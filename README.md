# USUARIO
API de orquestração de usuarios

# End Points
> Rota

- /login
- Descrição: Login do usuario

> Metodo 


- POST
- 

> Header Request

| KEY            | VALUE            |
|----------------|------------------|
| Content-Type   | application/json |
| correlation-id | UUID.Random()    |

> Body Request

| CAMPO          | EXEMPLO          |
|----------------|------------------|
| login          | tomate_123       |
| senha          | ax824kh12        |

> Header Response

| KEY            | EXEMPLO          |
|----------------|------------------|
| Authorization  | Bearer eyJhbGciO |

> Body Response

| CAMPO          | EXEMPLO                              |
|----------------|--------------------------------------|
| id_usuario     | dab7fcb2-24fd-4733-b680-d0389ce23b19 |
| token          | Bearer eyJhbGciOd3nhdbdb23hjdbu293jd |

> Http Status Response

| HTTP STATUS    | SIGNIFICADO                |
|----------------|----------------------------|
| 200            | Login Efetuado com sucesso |
| 401            | Login ou Senha Invalido    |

> Rota

- /usuario
- Descrição: Cadastro do usuario

> Metodo

- POST

> Header Request

| KEY            | VALUE            |
|----------------|------------------|
| Content-Type   | application/json |
| correlation-id | UUID.Random()    |

> Body Request

| CAMPO          | EXEMPLO          |
|----------------|------------------|
| login          | tomate_123       |
| email          | tom@gmail.co     |
| senha          | ax824kh12        |
| nome           | senhor tomate    |
| telefone       | 912341234        |
| List<perfil>   | ROLE_CLIENTE     |

> Header Response

| KEY            | EXEMPLO          |
|----------------|------------------|
| Authorization  | Bearer eyJhbGciO |

> Body Response

| CAMPO          | EXEMPLO                              |
|----------------|--------------------------------------|
| id_usuario     | dab7fcb2-24fd-4733-b680-d0389ce23b19 |
| token          | Bearer eyJhbGciOd3nhdbdb23hjdbu293jd |

> Http Status Response

| HTTP STATUS    | SIGNIFICADO                |
|----------------|----------------------------|
| 200            | Login Efetuado com sucesso |
| 401            | Login ou Senha Invalido    |
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

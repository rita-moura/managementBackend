# Api REST que gerencia loja de aluguel de carros

## Tecnologias e ferramentas utilizadas

- Spring boot
- Java
- Banco de dados relacional MySQL
- Workbench
- Maven
- JPA (Java Persistence API)
- Hibernate
- Swagger para documentação da api

## Como utilizar o projeto

**1 -** Clone o repositório do projeto

Com autenticação SSH

```bash
git clone git@github.com:rita-moura/managementBackend.git
```

Com Https

```bash
git clone https://github.com/rita-moura/managementBackend.git
```

**2 -** Entre na pasta clonada e abra com a idea de sua preferencia

```bash
cd pasta_do_projeto
```

**3-** Rode o script SQL, disponível no seguinte caminho src/main/resources/data.sql. Utilize a ferramenta de sua preferencia

- Esse script vai criar o banco de dados, as tabelas e adicionar alguns valores de exemplo. Caso queira alterar o nome atente-se a fazer isso em todos os lugares necessários, que são os apresentados abaixo

- No script que cria o banco e o utiliza

```sql
CREATE DATABASE novo_nome;
USE novo_nome;
```

- E no arquivo de configuração do banco de dados, que se encontra em src/main/resources/application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/novo_nome
```

**4 -** Altere a senha e o usuário do banco de dados no arquivo que encontra-se no seguinte caminho src/main/resources/application.properties

```properties
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
```

**5 -** Rode os seguintes comandos

- Limpa o target e compila o projeto

```bash
mvn clean install
```

- Roda o projeto e sobe o servidor

```bash
mvn spring-boot:run 
```

**6 -** Após subir o servidor abra o link abaixo para entrar na documentação do swagger

[Swagger](http://localhost:8080/swagger-ui/index.html)

Pronto pode brincar com sua api e fazer as alterações que quiser, qualquer dúvida fique a vontade para entrar em contato por aqui ou pelo
[Linkedin](https://www.linkedin.com/in/rita-moura-dev/)

## Informações sobre a api

A API é um CRUD (Create, Read, Update and Delete). O usuário pode criar, ler, atualizar veiculos e reservas

### Rotas

### Rota de veículos <http://localhost:8080/vehicles>

#### GET Veículo

- Retorna todos os veículos disponiveis

#### POST Veículo

- Adiciona um novo veículo ao banco de dados

  - Padrão para adicionar um novo veículo
  - Não aceita campos nulos
  - O `id` não precisa ser informado, pois é incrementado automaticamente
  - O campo `plate` só aceita 7 caracters e é unico, se já tiver o valor informado no banco de dados ele não deixa adicionar
  - E o campo `year` só aceita valores do tipo ano

    ```json
        {
            "brand": "Ford",
            "color": "Preto",
            "model": "Fordka",
            "plate": "HUJ1542",
            "year": 2023,
            "reserved": false
        }
    ```

#### PUT Veículo

- Altera o veículo de acordo com o `id` passado

  - Padrão para alteração
  - Nesse caso o `id` do veículo que será alterado deve ser informado

      ```json
        {
            "id": 1,
            "brand": "Ford",
            "color": "Preto",
            "model": "Fordka",
            "plate": "HUJ1542",
            "year": 2023,
            "reserved": false
        }
    ```

#### DELETE Veículo

- A rota nesse caso fica assim <http://localhost:8080/reservations?id=2>
- Exclui o veículo que corresponde ao `id` informado depois do sinal `=`

### Rota de reservas <http://localhost:8080/reservations>

#### GET Reserva

- Retorna todas as reservas

#### POST Reserva

- Adiciona uma nova reserva

#### DELETE Reserva

- A rota nesse caso fica assim <http://localhost:8080/reservations?id=2>
- Exclui a reserva com base no `id` informado depois do sinal `=`

### Dicas úteis

- Caso tenha dificuldades para acessar verifique se uma das portas não estão sendo utilizadas, por padrão o spring boot utiliza a porta `8080` e o MySQL a `3306`

- Caso tenha dificuldades com a conexão do banco certifique-se de que o banco de dados existe, que o nome ta configurado corretamente, e de que a senha e o usuário Mysql estão corretos

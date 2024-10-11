# Projeto: Sistema de Clientes e Produtos

Este projeto consiste em uma aplicação Java Maven conectada a um banco de dados PostgreSQL. O banco de dados e a aplicação são configurados para serem executados em containers Docker.

## Requisitos

Antes de executar este projeto, certifique-se de que você tenha os seguintes itens instalados em sua máquina:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Como executar o projeto

Siga os passos abaixo para executar o projeto em um ambiente Docker:

1. **Clonar o repositório**

    Clone este repositório em sua máquina local usando o comando:

    ```bash
    git clone https://github.com/vitormonteiropereira/ProjetoJDBC-Clientes-Produto.git
    ```

2. **Acessar o diretório do projeto**

    Entre no diretório do projeto:

    ```bash
    cd ProjetoJDBC-Clientes-Produto
    ```

3. **Compilar o projeto**

    Antes de executar o Docker, é necessário compilar o projeto Java. Execute o comando Maven abaixo para gerar o arquivo JAR:

    ```bash
    mvn clean package
    ```

4. **Executar o Docker Compose**

    Com o arquivo JAR gerado e o Docker instalado, execute o comando abaixo para subir os containers da aplicação e do banco de dados:

    ```bash
    docker-compose up --build
    ```

    Este comando irá:

    - **Construir** a imagem do aplicativo Java.
    - **Iniciar** um container PostgreSQL com os dados inicializados no banco de dados.
    - **Subir** a aplicação no endereço `http://localhost:8080`.

5. **Acessar a aplicação**

    Após o Docker Compose iniciar os serviços, a aplicação estará disponível em:

    ```
    http://localhost:8080
    ```

    O banco de dados PostgreSQL estará disponível na porta `5432`.

## Estrutura do Projeto

- **app**: Diretório contendo o código-fonte da aplicação Java.
- **db**: Diretório contendo o arquivo de inicialização do banco de dados (`init.sql`), onde são criadas as tabelas `clientes` e `produtos`.

## Como parar os containers

Para parar e remover os containers, execute o comando:

```bash
docker-compose down

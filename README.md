# ServiceFlow API

API REST para gerenciamento de chamados de suporte técnico.

O projeto está sendo desenvolvido com **Java e Spring Boot**, utilizando **PostgreSQL** como banco de dados e seguindo uma estrutura voltada para boas práticas de desenvolvimento de APIs REST.

## Tecnologias

* Java 21
* Spring Boot 4
* Spring Web MVC
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Docker
* JUnit
* Mockito

## Objetivo

O ServiceFlow tem como objetivo simular uma API de gerenciamento de chamados de suporte técnico, permitindo evoluir gradualmente funcionalidades comuns encontradas em sistemas corporativos.

O projeto será desenvolvido de forma incremental, priorizando uma implementação simples e adequada ao nível júnior, sem adicionar complexidade desnecessária.

## Funcionalidades planejadas

* Cadastro de usuários
* Cadastro de chamados
* Consulta de chamados
* Atualização de chamados
* Alteração de status
* Associação de chamados a usuários
* Validação de dados
* Persistência com PostgreSQL
* Tratamento de erros da API
* Testes automatizados

## Estrutura do projeto

A aplicação segue a estrutura padrão de um projeto Spring Boot:

```text
serviceflow-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/serviceflow/api/
│   │   │       ├── controller/
│   │   │       │   └── ServiceRequestController.java
│   │   │       ├── entity/
│   │   │       │   ├── ServiceRequest.java
│   │   │       │   └── ServiceRequestStatus.java
│   │   │       ├── repository/
│   │   │       │   └── ServiceRequestRepository.java
│   │   │       ├── service/
│   │   │       │   └── ServiceRequestService.java
│   │   │       └── ServiceflowApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/serviceflow/api/
│               ├── repository/
│               │   └── ServiceRequestRepositoryTest.java
│               ├── service/
│               │   └── ServiceRequestServiceTest.java
│               └── ServiceflowApiApplicationTests.java
├── .gitignore
├── pom.xml
├── mvnw
└── README.md
```

## Banco de dados

O projeto utiliza PostgreSQL para persistência dos dados.

Durante o desenvolvimento local, o PostgreSQL pode ser executado utilizando Docker.

Exemplo:

```bash
docker run --name serviceflow-postgres \
  -e POSTGRES_DB=serviceflow \
  -e POSTGRES_USER=serviceflow \
  -e POSTGRES_PASSWORD=serviceflow_dev \
  -p 5432:5432 \
  -d postgres
```

A aplicação utiliza a seguinte configuração local:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/serviceflow
spring.datasource.username=serviceflow
spring.datasource.password=serviceflow_dev
```

> As credenciais apresentadas acima são destinadas exclusivamente ao ambiente de desenvolvimento local. Em ambientes reais, as credenciais devem ser armazenadas de forma segura, por exemplo através de variáveis de ambiente ou mecanismos de gerenciamento de secrets.

## Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/lgomesroc/serviceflow-api.git
cd serviceflow-api
```

## PostgreSQL

O projeto utiliza um contêiner PostgreSQL executado através do Docker.

Antes de executar a aplicação, o **Docker deve estar em execução e o contêiner `serviceflow-postgres` também deve estar iniciado.**

Verifique:

```bash
docker ps
```

O contêiner deve aparecer com a porta:

`0.0.0.0:5432->5432/tcp`

Caso o contêiner já exista, mas esteja parado:

```bash
docker start serviceflow-postgres
```

Caso ainda não exista, crie o contêiner:

```bash
docker run --name serviceflow-postgres \
  -e POSTGRES_DB=serviceflow \
  -e POSTGRES_USER=serviceflow \
  -e POSTGRES_PASSWORD=serviceflow_dev \
  -p 5432:5432 \
  -d postgres:17
```

A aplicação utiliza:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/serviceflow
spring.datasource.username=serviceflow
spring.datasource.password=serviceflow_dev
```

Com o PostgreSQL em execução, execute os testes:

```bash
./mvnw test
```

Execute o projeto:
```bash
./mvnw spring-boot:run
```

## Build

Para gerar o arquivo JAR:

```bash
./mvnw clean package
```

O artefato será gerado em:

```text
target/serviceflow-api-0.0.1-SNAPSHOT.jar
```

## Status do projeto

Em desenvolvimento.

O projeto está sendo construído de forma incremental, começando pela configuração da aplicação, integração com PostgreSQL e estrutura inicial da API.

## Progresso do desenvolvimento

### Dia 1
- Configuração inicial do projeto Spring Boot
- Configuração do Maven Wrapper
- Configuração do PostgreSQL com Docker
- Configuração da conexão da aplicação com o banco de dados

### Dia 2
- Configuração do Spring Data JPA
- Configuração do Hibernate
- Validação da conexão com PostgreSQL
- Estrutura inicial do projeto documentada

### Dia 3
- Criação da entidade `ServiceRequest`
- Criação do enum `ServiceRequestStatus`
- Configuração do mapeamento JPA para a tabela `service_requests`
- Criação do `ServiceRequestRepository` utilizando Spring Data JPA
- Configuração automática de `status` e `createdAt` através de `@PrePersist`
- Criação de teste automatizado para persistência
- Validação da persistência de uma solicitação no PostgreSQL
- Testes executados com sucesso: **2 testes, 0 falhas, 0 erros**

### Dia 4
- Criação da camada de serviço `ServiceRequestService`
- Criação da camada REST `ServiceRequestController`
- Implementação do cadastro de solicitações de serviço
- Implementação da consulta de solicitações de serviço
- Integração entre Controller, Service e Repository
- Criação de teste automatizado para a camada de serviço utilizando Mockito
- Validação da criação de solicitações através da API REST
- Validação da persistência dos dados no PostgreSQL
- Validação da consulta de solicitações através da API REST
- Testes executados com sucesso: **3 testes, 0 falhas, 0 erros**

### Dia 5
- Implementação da consulta de uma solicitação de serviço por ID
- Implementação da atualização de uma solicitação de serviço por ID
- Atualização dos campos `title` e `description` de uma solicitação existente
- Preservação dos campos `id`, `status` e `createdAt` durante a atualização
- Criação de testes automatizados para atualização de solicitações utilizando Mockito
- Criação de teste automatizado para atualização de uma solicitação inexistente
- Validação da consulta por ID através da API REST
- Validação da atualização através da API REST
- Validação da atualização de uma solicitação inexistente
- Execução da suíte completa de testes com sucesso: 5 testes, 0 falhas, 0 erros
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso

## Próxima aula

### Dia 6 — Tratamento de exceções e respostas HTTP

- Criação de exceção específica para solicitação de serviço não encontrada
- Implementação do tratamento de recursos inexistentes
- Retorno de `404 Not Found` quando uma solicitação não for encontrada
- Criação de tratamento global de exceções da API
- Padronização das respostas de erro
- Atualização do fluxo de consulta por ID
- Atualização do fluxo de alteração por ID
- Criação de testes automatizados para recursos inexistentes
- Validação do comportamento da API através de requisições HTTP
- Validação dos endpoints existentes após a implementação do tratamento de exceções
- Execução da suíte completa de testes
- Empacotamento da aplicação com `./mvnw clean package`

## Resumo
Dia 1 → Configuração inicial<br>
Dia 2 → JPA + PostgreSQL<br>
Dia 3 → Entidade + Repository + persistência<br>
Dia 4 → Service + Controller + endpoints REST<br>
Dia 5 → Consulta por ID + atualização de solicitações<br>
Dia 6 → Tratamento de exceções e respostas HTTP<br>

## Autor

Luciano Rocha

Desenvolvedor Backend / Full Stack Júnior

# ServiceFlow API

API REST para gerenciamento de chamados de suporte técnico.

O projeto está sendo desenvolvido com **Java e Spring Boot**, utilizando **PostgreSQL** como banco de dados e seguindo uma estrutura voltada para boas práticas de desenvolvimento de APIs REST.

## Índice

- [Tecnologias](#tecnologias)
- [Objetivo](#objetivo)
- [Funcionalidades planejadas](#funcionalidades-planejadas)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Banco de dados](#banco-de-dados)
- [Executando o projeto](#executando-o-projeto)
- [Build](#build)
- [Status do projeto](#status-do-projeto)
- [Progresso do desenvolvimento](#progresso-do-desenvolvimento)
    - [Aula 1 - Configuração inicial e integração com PostgreSQL](#aula-1---configuração-inicial-e-integração-com-postgresql)
    - [Aula 2 - JPA, Hibernate e persistência de dados](#aula-2---jpa-hibernate-e-persistência-de-dados)
    - [Aula 3 - Entidade ServiceRequest e Repository](#aula-3---entidade-servicerequest-e-repository)
    - [Aula 4 - Camadas Service e Controller e primeiros endpoints REST](#aula-4---camadas-service-e-controller-e-primeiros-endpoints-rest)
    - [Aula 5 - Consulta e atualização de solicitações por ID](#aula-5---consulta-e-atualização-de-solicitações-por-id)
    - [Aula 6 - Tratamento de exceções e respostas HTTP](#aula-6---tratamento-de-exceções-e-respostas-http)
    - [Aula 7 - Validação de dados da API](#aula-7---validação-de-dados-da-api)
    - [Aula 8 - Testes dos endpoints e cobertura da API](#aula-8---testes-dos-endpoints-e-cobertura-da-api)
    - [Próxima aula](#próxima-aula)
- [Resumo](#resumo)
- [Autor](#autor)

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
│   │   │       ├── exception/ 
│   │   │       │ ├── ErrorResponse.java
│   │   │       │ ├── GlobalExceptionHandler.java 
│   │   │       │ └── ServiceRequestNotFoundException.java
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
│               ├── controller/
│               │   └── ServiceRequestControllerTest.java
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

### Aula 1 - Configuração inicial e integração com PostgreSQL

- Configuração inicial do projeto Spring Boot
- Configuração do Maven Wrapper
- Configuração do PostgreSQL com Docker
- Configuração da conexão da aplicação com o banco de dados

### Aula 2 - JPA, Hibernate e persistência de dados

- Configuração do Spring Data JPA
- Configuração do Hibernate
- Validação da conexão com PostgreSQL
- Estrutura inicial do projeto documentada

### Aula 3 - Entidade ServiceRequest e Repository

- Criação da entidade `ServiceRequest`
- Criação do enum `ServiceRequestStatus`
- Configuração do mapeamento JPA para a tabela `service_requests`
- Criação do `ServiceRequestRepository` utilizando Spring Data JPA
- Configuração automática de `status` e `createdAt` através de `@PrePersist`
- Criação de teste automatizado para persistência
- Validação da persistência de uma solicitação no PostgreSQL
- Testes executados com sucesso: **2 testes, 0 falhas, 0 erros**

### Aula 4 - Camadas Service e Controller e primeiros endpoints REST

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

### Aula 5 - Consulta e atualização de solicitações por ID

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

### Aula 6 — Tratamento de exceções e respostas HTTP

- Criação da exceção específica `ServiceRequestNotFoundException`
- Atualização do fluxo de consulta por ID para tratar solicitações inexistentes
- Atualização do fluxo de alteração por ID para tratar solicitações inexistentes
- Criação do `GlobalExceptionHandler` utilizando `@RestControllerAdvice`
- Implementação do tratamento global de `ServiceRequestNotFoundException`
- Retorno de `404 Not Found` para solicitações inexistentes
- Criação da classe `ErrorResponse` para padronização das respostas de erro
- Retorno de respostas de erro em formato JSON
- Criação de teste automatizado para consulta de solicitação inexistente
- Atualização do teste automatizado para alteração de solicitação inexistente
- Validação do `GET /api/service-requests/{id}` com recurso inexistente através de requisição HTTP
- Validação do `PUT /api/service-requests/{id}` com recurso inexistente através de requisição HTTP
- Validação da resposta `404 Not Found` com JSON padronizado
- Execução da suíte completa de testes com sucesso: **6 testes, 0 falhas, 0 erros**
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso

### Aula 7 - Validação de dados da API
- Adição das dependências necessárias para Bean Validation
- Utilização de `@NotBlank` nos campos obrigatórios de `ServiceRequest`
- Ativação da validação dos dados recebidos pelo Controller com `@Valid`
- Implementação do tratamento global de erros de validação
- Padronização das respostas de erro de validação através de `ErrorResponse`
- Retorno de `400 Bad Request` para dados inválidos
- Validação do campo `title`
- Validação do campo `description`
- Validação de dados inválidos no `POST /api/service-requests`
- Validação de dados inválidos no `PUT /api/service-requests/{id}`
- Validação de campos preenchidos apenas com espaços
- Criação de testes automatizados para validação de dados inválidos
- Criação de testes de Controller utilizando `MockMvc`
- Testes automatizados para `POST` com `title` inválido
- Testes automatizados para `POST` com `description` inválida
- Testes automatizados para `PUT` com `title` inválido
- Testes automatizados para `PUT` com `description` inválida
- Validação dos endpoints através de requisições HTTP com `curl`
- Validação de criação de solicitação com retorno `201 Created`
- Validação de atualização de solicitação com retorno `200 OK`
- Validação dos endpoints existentes após a implementação
- Execução da suíte completa de testes com sucesso: **10 testes, 0 falhas, 0 erros**
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso

### Aula 8 - Testes dos endpoints e cobertura da API

- Criação de testes automatizados para o `GET /api/service-requests`
- Teste de retorno `200 OK` na consulta da lista
- Validação da resposta JSON da consulta da lista
- Criação de teste automatizado para o `GET /api/service-requests/{id}`
- Teste de retorno `200 OK` para uma solicitação existente
- Validação dos campos retornados no JSON da solicitação
- Criação de teste automatizado para o `GET /api/service-requests/{id}` com ID inexistente
- Teste de retorno `404 Not Found` para solicitação inexistente
- Validação do JSON de erro retornado pelo `GlobalExceptionHandler`
- Criação de teste automatizado para o `POST /api/service-requests`
- Teste de retorno `201 Created` para criação válida
- Validação do JSON retornado após a criação
- Criação de teste automatizado para o `PUT /api/service-requests/{id}`
- Teste de retorno `200 OK` para atualização válida
- Validação do JSON retornado após a atualização
- Criação de teste automatizado para o `PUT /api/service-requests/{id}` com ID inexistente
- Teste de retorno `404 Not Found` para atualização de solicitação inexistente
- Revisão dos testes de `400 Bad Request`
- Revisão dos testes de `404 Not Found`
- Utilização de `MockMvc` nos testes dos endpoints
- Execução da suíte completa de testes com sucesso: **16 testes, 0 falhas, 0 erros**
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso
- Geração do arquivo JAR `serviceflow-api-0.0.1-SNAPSHOT.jar`

## Próxima aula

### Aula 9 - Testes unitários e integração

- Revisão da diferença entre testes unitários e testes de integração
- Revisão dos testes de Service utilizando Mockito
- Revisão dos testes de Repository
- Avaliação do isolamento dos testes
- Organização da estratégia de testes da aplicação
- Identificação dos testes que acessam o contexto do Spring
- Evolução da cobertura de testes sem adicionar complexidade desnecessária

## Resumo
✓ Aula 1 → Configuração inicial e integração com PostgreSQL<br>
✓ Aula 2 → JPA, Hibernate e persistência de dados<br>
✓ Aula 3 → Entidade ServiceRequest e Repository<br>
✓ Aula 4 → Camadas Service e Controller e primeiros endpoints REST<br>
✓ Aula 5 → Consulta e atualização de solicitações por ID<br>
✓ Aula 6 → Tratamento de exceções e respostas HTTP<br>
✓ Aula 7 → Validação de dados da API<br>
✓ Aula 8 → Testes dos endpoints e cobertura da API
- [ ] Aula 9 → Testes unitários e integração
- [ ] Aula 10 → DTOs e separação entre entidade e contrato da API
- [ ] Aula 11 → Mapeamento entre DTOs e entidades
- [ ] Aula 12 → Organização e melhoria da arquitetura da API
- [ ] Aula 13 → Alteração de status das solicitações
- [ ] Aula 14 → Regras de negócio para solicitações
- [ ] Aula 15 → Paginação e ordenação
- [ ] Aula 16 → Documentação da API com Swagger/OpenAPI
- [ ] Aula 17 → Testes adicionais e melhoria da cobertura
- [ ] Aula 18 → Segurança e autenticação da API
- [ ] Aula 19 → Perfis e configurações de ambiente
- [ ] Aula 20 → Preparação da aplicação para execução em ambiente de produção
- [ ] Aula 21 → Dockerização da aplicação
- [ ] Aula 22 → Integração entre aplicação e PostgreSQL utilizando Docker Compose
- [ ] Aula 23 → Logs e observabilidade básica
- [ ] Aula 24 → Tratamento de configurações e variáveis de ambiente
- [ ] Aula 25 → Revisão geral e refatoração
- [ ] Aula 26 → Testes finais e validação da API
- [ ] Aula 27 → Preparação do projeto para portfólio
- [ ] Aula 28 → Documentação final e README profissional
- [ ] Aula 29 → Revisão técnica para entrevistas
- [ ] Aula 30 → Finalização do projeto e apresentação técnica

## Autor

Luciano Rocha

Desenvolvedor Backend / Full Stack Júnior

# ServiceFlow API

API REST para gerenciamento de chamados de suporte técnico.

O projeto está sendo desenvolvido com **Java e Spring Boot**, utilizando **PostgreSQL** como banco de dados e seguindo uma estrutura voltada para boas práticas de desenvolvimento de APIs REST.

## Índice

- [Tecnologias](#tecnologias)
- [Objetivo](#objetivo)
- [Funcionalidades](#funcionalidades)
  - [Implementadas](#implementadas)
  - [Planejadas](#planejadas)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Banco de dados](#banco-de-dados)
- [Executando o projeto](#executando-o-projeto)
  - [PostgreSQL](#postgresql)
  - [Build](#build)
- [Documentação da API](#documentação-da-api)
  - [Requisitos](#requisitos)
  - [Paginação e ordenação](#paginação-e-ordenação)
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
    - [Aula 9 - Testes unitários e integração](#aula-9---testes-unitários-e-integração)
    - [Aula 10 - DTOs e separação entre entidade e contrato da API](#aula-10---dtos-e-separação-entre-entidade-e-contrato-da-api)
    - [Aula 11 - Mapeamento entre DTOs e entidades](#aula-11---mapeamento-entre-dtos-e-entidades)
    - [Aula 12 - Organização e melhoria da arquitetura da API](#aula-12---organização-e-melhoria-da-arquitetura-da-api)
    - [Aula 13 - Alteração de status das solicitações](#aula-13---alteração-de-status-das-solicitações)
    - [Aula 14 - Regras de negócio para solicitações](#aula-14---regras-de-negócio-para-solicitações)
    - [Aula 15 - Documentação da API](#aula-15---documentação-da-api)
    - [Aula 16 - Paginação e ordenação](#aula-16---paginação-e-ordenação)
- [Próximas aulas](#próximas-aulas)
    - [Aula 17 - Testes adicionais e melhoria da cobertura](#aula-17---testes-adicionais-e-melhoria-da-cobertura)
    - [Aula 18 - Perfis e configurações de ambiente](#aula-18---perfis-e-configurações-de-ambiente)
    - [Aula 19 - Dockerização da aplicação](#aula-19---dockerização-da-aplicação)
    - [Aula 20 - Docker Compose e ambiente da aplicação](#aula-20---docker-compose-e-ambiente-da-aplicação)
- [Resumo](#resumo)
- [Autor](#autor)

## Tecnologias

* **Java 21** — versão LTS do Java utilizada no projeto, oferecendo estabilidade, recursos modernos da linguagem e suporte de longo prazo.
* **Spring Boot 4** — escolhido para simplificar a configuração e o desenvolvimento da aplicação, permitindo estruturar a API REST sem configurações desnecessárias.
* **Spring Web MVC** — utilizado para criação dos Controllers e implementação dos endpoints HTTP da API REST.
* **Spring Data JPA** — utilizado para simplificar o acesso aos dados e a implementação do Repository, reduzindo código repetitivo de persistência.
* **Hibernate** — utilizado como implementação JPA para realizar o mapeamento entre as entidades Java e as tabelas do banco de dados.
* **PostgreSQL** — escolhido como banco de dados relacional por ser robusto, amplamente utilizado em aplicações corporativas e adequado ao modelo de dados do projeto.
* **Maven** — utilizado para gerenciamento de dependências, configuração do projeto e execução do ciclo de build e testes.
* **Docker** — utilizado para executar o PostgreSQL em ambiente local de forma isolada e reproduzível, sem necessidade de instalar o banco diretamente no sistema operacional.
* **JUnit** — utilizado para criação e execução dos testes automatizados.
* **Mockito** — utilizado nos testes unitários para criar mocks das dependências e permitir o isolamento da camada Service.
* **OpenAPI** — utilizado para definir e descrever o contrato da API REST.
* **Swagger UI** — utilizado para disponibilizar uma interface web para consulta e teste dos endpoints da API.

> As tecnologias foram escolhidas considerando o objetivo do projeto: construir uma API REST com uma stack comum no desenvolvimento backend corporativo, mantendo a implementação simples e adequada ao nível júnior.

## Objetivo

O ServiceFlow tem como objetivo simular uma API de gerenciamento de chamados de suporte técnico, permitindo evoluir gradualmente funcionalidades comuns encontradas em sistemas corporativos.

O projeto será desenvolvido de forma incremental, priorizando uma implementação simples e adequada ao nível júnior, sem adicionar complexidade desnecessária.

## Funcionalidades

### Implementadas

* Cadastro de chamados
* Consulta de chamados
* Atualização de chamados
* Alteração de status
* Validação de dados
* Persistência com PostgreSQL
* Tratamento de erros da API
* Testes automatizados
* Documentação da API com OpenAPI e Swagger

### Planejadas

- [ ] Paginação e ordenação
- [ ] Configurações por ambiente
- [ ] Dockerização da aplicação
- [ ] Docker Compose
- [ ] Logs e observabilidade básica

## Estrutura do projeto

A aplicação segue a estrutura padrão de um projeto Spring Boot:

```text
serviceflow-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/serviceflow/api/
│   │   │   │   ├── config/
│   │   │   │   │   └── OpenApiConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   └── ServiceRequestController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── ServiceRequestRequest.java
│   │   │   │   │   ├── ServiceRequestResponse.java
│   │   │   │   │   └── ServiceRequestStatusRequest.java
│   │   │   │   ├── entity/
│   │   │   │   │   ├── ServiceRequest.java
│   │   │   │   │   └── ServiceRequestStatus.java
│   │   │   │   ├── exception/ 
│   │   │   │   │   ├── ErrorResponse.java
│   │   │   │   │   ├── GlobalExceptionHandler.java 
│   │   │   │   │   ├── InvalidServiceRequestStateException.java
│   │   │   │   │   └── ServiceRequestNotFoundException.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── ServiceRequestRepository.java
│   │   │   │   ├── service/
│   │   │   │   │   └── ServiceRequestService.java
│   │   │   │   └── ServiceflowApiApplication.java
│   │   └── resources/
│   │   │   └── application.properties
│   └── test/
│   │   └── java/
│   │   │   └── com/serviceflow/api/
│   │   │   │   ├── controller/
│   │   │   │   │   └── ServiceRequestControllerTest.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── ServiceRequestRepositoryTest.java
│   │   │   │   ├── service/
│   │   │   │   │   └── ServiceRequestServiceTest.java
│   │   │   │   └── ServiceflowApiApplicationTests.java
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

### PostgreSQL

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

### Build

Para gerar o arquivo JAR:

```bash
./mvnw clean package
```

O artefato será gerado em:

```text
target/serviceflow-api-0.0.1-SNAPSHOT.jar
```

## Documentação da API

A API possui documentação baseada em **OpenAPI 3.1**, disponibilizada através do Swagger UI.

Com a aplicação em execução, a documentação pode ser acessada através de:

**Swagger UI:**

http://localhost:8080/swagger-ui/index.html

**OpenAPI JSON:**

http://localhost:8080/v3/api-docs

O Swagger UI permite visualizar e testar os endpoints da API diretamente pelo navegador, incluindo parâmetros, dados de requisição e respostas HTTP.

### Requisitos

Para utilizar a API localmente, é necessário que:

- A aplicação Spring Boot esteja em execução.
- O contêiner PostgreSQL `serviceflow-postgres` esteja em execução.
- O PostgreSQL esteja disponível na porta `5432`.

Verifique o contêiner com:

```bash
docker ps
```

Caso o contêiner esteja parado:

```bash
docker start serviceflow-postgres
```

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

A documentação disponibiliza os endpoints de solicitações de serviço e os principais códigos de resposta utilizados pela API, incluindo `200 OK`, `201 Created`, `400 Bad Request`, `404 Not Found` e `409 Conflict`.

### Paginação e ordenação

A documentação do endpoint `GET /api/service-requests` também contempla os parâmetros de paginação e ordenação:

- `page` — número da página, iniciando em `0`.
- `size` — quantidade de solicitações retornadas por página.
- `sort` — campo e direção utilizados para ordenação, por exemplo `createdAt,desc`.

A paginação e a ordenação foram implementadas utilizando os recursos do Spring Data e podem ser testadas diretamente através do Swagger UI.

## Status do projeto

Em desenvolvimento.

O projeto está sendo desenvolvido de forma incremental, evoluindo de uma API REST básica para uma aplicação com persistência em PostgreSQL, validação de dados, tratamento de exceções, regras de negócio, testes automatizados, documentação com OpenAPI/Swagger, paginação e ordenação dos resultados.

Até o momento, foram concluídas **16 aulas**, contemplando a implementação e validação das principais funcionalidades da API.

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

### Aula 9 - Testes unitários e integração

- Revisão da diferença entre testes unitários e testes de integração
- Revisão dos testes da camada Service utilizando Mockito
- Validação do isolamento da camada Service em relação ao PostgreSQL
- Revisão do teste de integração do Repository com PostgreSQL
- Revisão dos testes de integração dos endpoints utilizando MockMvc
- Identificação dos testes que carregam o contexto do Spring através de `@SpringBootTest`
- Identificação do teste de contexto da aplicação através de `ServiceflowApiApplicationTests`
- Fortalecimento do teste de atualização da camada Service com validação dos dados alterados
- Melhoria do isolamento dos testes de Controller evitando dependência de IDs previamente existentes no banco
- Criação dos dados necessários durante os próprios testes de integração
- Validação da estratégia de testes por responsabilidade: Service, Repository, Controller e contexto da aplicação
- Execução da suíte completa de testes com sucesso: **16 testes, 0 falhas, 0 erros**
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso
- Geração do arquivo JAR `serviceflow-api-0.0.1-SNAPSHOT.jar`

### Aula 10 - DTOs e separação entre entidade e contrato da API

- Introdução ao conceito de DTO (Data Transfer Object)
- Separação entre o modelo de persistência e o contrato HTTP da API
- Criação do `ServiceRequestRequest` para representar os dados de entrada
- Criação do `ServiceRequestResponse` para representar os dados de saída
- Remoção da exposição direta da entidade `ServiceRequest` pelo Controller
- Atualização do Controller para receber `ServiceRequestRequest`
- Atualização do Controller para retornar `ServiceRequestResponse`
- Atualização da camada Service para converter DTOs e entidades
- Preservação das validações com `@Valid` e `@NotBlank`
- Remoção das validações HTTP da entidade JPA `ServiceRequest`
- Atualização dos testes da camada Service para trabalhar com os novos DTOs
- Revisão dos testes dos endpoints após a alteração do contrato da API
- Execução da suíte completa de testes com sucesso: **16 testes, 0 falhas, 0 erros**
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso
- Geração do arquivo JAR `serviceflow-api-0.0.1-SNAPSHOT.jar`

### Aula 11 - Mapeamento entre DTOs e entidades

- Introdução ao conceito de mapeamento entre DTOs e entidades
- Identificação das conversões entre `ServiceRequestRequest`, `ServiceRequest` e `ServiceRequestResponse`
- Identificação da conversão `ServiceRequestRequest → ServiceRequest` realizada durante a criação de solicitações
- Identificação da conversão `ServiceRequest → ServiceRequestResponse` utilizada nas respostas da API
- Extração da conversão `ServiceRequestRequest → ServiceRequest` para o método privado `toEntity()`
- Manutenção da conversão `ServiceRequest → ServiceRequestResponse` através do método privado `toResponse()`
- Manutenção da atualização diretamente sobre a entidade existente para preservar campos como `id`, `status` e `createdAt`
- Avaliação da criação de uma classe específica para mapeamento
- Decisão de não criar um `ServiceRequestMapper`, pois as conversões são simples, específicas e não apresentam complexidade suficiente para justificar uma nova classe
- Aplicação do mapeamento mantendo a estrutura atual da camada Service
- Evitada a introdução de bibliotecas ou frameworks de mapeamento para manter a implementação simples e adequada ao nível júnior
- Execução da suíte completa de testes com sucesso: **16 testes, 0 falhas, 0 erros**
- Empacotamento da aplicação com `./mvnw clean package` executado com sucesso
- Commit da alteração com `refactor: organize dto to entity mapping`

### Aula 12 - Organização e melhoria da arquitetura da API
- Análise da estrutura atual de Controller, Service, Repository e Entity
- Revisão das responsabilidades de cada camada
- Verificação da separação de responsabilidades entre as camadas
- Identificação de responsabilidades que possam estar misturadas
- Revisão da estrutura de pacotes
- Revisão da nomenclatura de classes e métodos
- Identificação de possíveis simplificações e melhorias no código
- Avaliação de melhorias arquiteturais sem adicionar complexidade desnecessária
- Verificação da utilização dos DTOs no fluxo da API
- Confirmação de que o Controller não contém regras de negócio
- Confirmação de que o Repository permanece responsável pela persistência
- Confirmação de que o Service concentra a lógica de aplicação e o mapeamento simples entre DTOs e entidades
- Avaliação da necessidade de criação de uma classe `ServiceRequestMapper`
- Decisão de não criar um Mapper, pois as conversões continuam simples e específicas
- Avaliação da necessidade de novas camadas ou abstrações
- Decisão de não adicionar novas camadas ou abstrações por falta de justificativa técnica
- Manutenção da arquitetura atual por estar adequada ao tamanho e ao objetivo do projeto
- Refatoração estrutural não realizada por não haver necessidade técnica
- Execução da suíte completa de testes com sucesso: **16 testes, 0 falhas, 0 erros**
- Confirmação do `BUILD SUCCESS`

### Aula 13 - Alteração de status das solicitações

- Revisão do enum `ServiceRequestStatus`
- Criação do DTO `ServiceRequestStatusRequest` para representar a alteração de status
- Implementação do endpoint `PATCH /api/service-requests/{id}/status`
- Definição do fluxo de alteração de status entre Controller, Service e Repository
- Validação do status recebido através de `@NotNull`
- Validação da existência da solicitação antes da alteração
- Tratamento de solicitação inexistente através de `ServiceRequestNotFoundException`
- Atualização do status diretamente na entidade existente, preservando os demais dados da solicitação
- Persistência do novo status através do `ServiceRequestRepository`
- Retorno da solicitação atualizada através de `ServiceRequestResponse`
- Criação de teste unitário para alteração de status
- Criação de teste unitário para alteração de status de solicitação inexistente
- Criação de testes de Controller para alteração de status
- Criação de teste de validação para status nulo
- Validação manual do `PATCH /api/service-requests/{id}/status` através de `curl`
- Validação manual da persistência do novo status através do `GET /api/service-requests/{id}`
- Validação manual do retorno `404 Not Found` para solicitação inexistente
- Execução da suíte completa de testes com sucesso: **20 testes, 0 falhas, 0 erros**
- Confirmação do `BUILD SUCCESS`

### Aula 14 - Regras de negócio para solicitações

- Definição das regras de transição entre os status das solicitações.
- Validação das transições permitidas entre `PENDING`, `IN_PROGRESS`, `COMPLETED` e `CANCELLED`.
- Impedimento de transições inválidas de status.
- Definição de `COMPLETED` e `CANCELLED` como estados finais.
- Impedimento de alteração de solicitações que estejam em status final.
- Centralização das regras de negócio na camada Service.
- Criação da exceção `InvalidServiceRequestStateException`.
- Tratamento global das exceções de regra de negócio.
- Retorno HTTP `409 Conflict` para violações das regras de negócio.
- Criação de testes unitários para as transições permitidas.
- Criação de testes unitários para as transições não permitidas.
- Criação de testes para impedir alterações em solicitações finalizadas.
- Criação de testes de Controller para validação das regras de negócio.
- Execução da suíte completa de testes.
- Resultado final: **39 testes executados, 0 falhas e 0 erros**.

### Aula 15 - Documentação da API

- Introdução à documentação de APIs REST.
- Diferença entre OpenAPI, Swagger, Postman e Insomnia.
- Adição da documentação OpenAPI ao projeto.
- Configuração do Swagger UI.
- Criação da classe `OpenApiConfig`.
- Organização da configuração do OpenAPI na pasta `config`.
- Documentação dos endpoints de solicitações de serviço.
- Documentação dos parâmetros das requisições.
- Documentação dos corpos de requisição e resposta.
- Documentação dos principais códigos de resposta HTTP.
- Inclusão do schema `ErrorResponse` na documentação.
- Validação da documentação através do Swagger UI.
- Teste do `POST /api/service-requests` através do Swagger UI.
- Teste do `GET /api/service-requests` através do Swagger UI.
- Teste do `GET /api/service-requests/{id}` com recurso existente.
- Teste do `GET /api/service-requests/{id}` com recurso inexistente, validando `404 Not Found`.
- Teste do `PUT /api/service-requests/{id}`.
- Teste do `PATCH /api/service-requests/{id}/status`.
- Teste de violação de regra de negócio, validando `409 Conflict`.
- Validação da resposta `ErrorResponse` para erros da API.
- Execução da suíte completa de testes com sucesso: **39 testes, 0 falhas, 0 erros**.
- Confirmação do `BUILD SUCCESS`.
- Documentação das formas de acesso ao Swagger UI e ao documento OpenAPI.

### Aula 16 - Paginação e ordenação

- Introdução ao conceito de paginação de resultados em APIs REST.
- Implementação de paginação no endpoint `GET /api/service-requests`.
- Utilização de `Page`, `Pageable` e `PageRequest` do Spring Data.
- Definição do parâmetro `page` para selecionar a página dos resultados.
- Definição do parâmetro `size` para controlar a quantidade de registros retornados.
- Implementação de ordenação através do parâmetro `sort`.
- Definição da ordenação padrão por `createdAt` em ordem decrescente.
- Permissão para informar diferentes campos e direções de ordenação através do Swagger UI.
- Retorno das informações de paginação através do objeto `Page`.
- Validação do número da página, quantidade de elementos e total de registros retornados.
- Atualização da documentação do endpoint no Swagger UI.
- Atualização dos testes do Controller para contemplar o comportamento paginado.
- Validação de diferentes páginas através do Swagger UI.
- Validação da ordenação por `createdAt,desc` através do Swagger UI.
- Validação de que páginas diferentes retornam conjuntos diferentes de solicitações.
- Validação de que o tamanho da página é respeitado.
- Execução da suíte completa de testes com sucesso: **43 testes, 0 falhas e 0 erros**.
- Confirmação do `BUILD SUCCESS`.

## Próximas aulas

### Aula 17 - Testes adicionais e melhoria da cobertura

- Revisão da suíte atual de testes.
- Identificação de cenários ainda não testados.
- Criação de testes para casos de borda.
- Ampliação dos testes das regras de negócio.
- Ampliação dos testes de validação.
- Ampliação dos testes dos endpoints.
- Verificação dos cenários de erro HTTP.
- Revisão dos testes existentes para evitar duplicação.
- Execução da suíte completa de testes.
- Análise da cobertura dos principais fluxos da aplicação.

### Aula 18 - Perfis e configurações de ambiente

- Introdução aos perfis de configuração do Spring Boot.
- Separação das configurações de desenvolvimento e teste.
- Criação de configurações específicas por ambiente.
- Revisão das configurações de acesso ao PostgreSQL.
- Utilização de variáveis de ambiente para configurações sensíveis.
- Validação da aplicação utilizando diferentes configurações.
- Execução dos testes após a alteração das configurações.

### Aula 19 - Dockerização da aplicação

- Introdução à execução da aplicação Spring Boot em Docker.
- Criação do `Dockerfile`.
- Utilização do JAR da aplicação na construção da imagem Docker.
- Criação da imagem Docker da API.
- Execução da API em um contêiner.
- Configuração da comunicação entre a API e o PostgreSQL.
- Validação dos endpoints com a aplicação executando em Docker.
- Execução dos testes e validação do funcionamento da aplicação.

### Aula 20 - Docker Compose e ambiente da aplicação

- Introdução ao Docker Compose.
- Criação do `docker-compose.yml`.
- Configuração do serviço da API.
- Configuração do serviço PostgreSQL.
- Configuração da comunicação entre os contêineres.
- Configuração das variáveis de ambiente.
- Inicialização da aplicação e do banco através do Docker Compose.
- Validação da API através do Swagger UI.
- Validação da persistência dos dados no PostgreSQL.
- Execução da suíte completa de testes.
- Documentação dos comandos necessários para iniciar o ambiente.

## Resumo
✓ Aula 1 → Configuração inicial e integração com PostgreSQL<br>
✓ Aula 2 → JPA, Hibernate e persistência de dados<br>
✓ Aula 3 → Entidade ServiceRequest e Repository<br>
✓ Aula 4 → Camadas Service e Controller e primeiros endpoints REST<br>
✓ Aula 5 → Consulta e atualização de solicitações por ID<br>
✓ Aula 6 → Tratamento de exceções e respostas HTTP<br>
✓ Aula 7 → Validação de dados da API<br>
✓ Aula 8 → Testes dos endpoints e cobertura da API<br>
✓ Aula 9 → Testes unitários e integração<br>
✓ Aula 10 → DTOs e separação entre entidade e contrato da API<br>
✓ Aula 11 → Mapeamento entre DTOs e entidades<br>
✓ Aula 12 → Organização e melhoria da arquitetura da API<br>
✓ Aula 13 → Alteração de status das solicitações<br>
✓ Aula 14 → Regras de negócio para solicitações<br>
✓ Aula 14 → Regras de negócio para solicitações<br>
✓ Aula 15 → Documentação da API com Swagger/OpenAPI<br>
✓ Aula 16 → Paginação e ordenação<br>
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

Tínhamos aproximadamente:
```text
HTTP
↓
ServiceRequest
↓
Service
↓
Repository
↓
PostgreSQL
```

Agora temos:
```
HTTP
↓
DTO
↓
Service
↓
Entity
↓
Repository
↓
PostgreSQL
```

> Na Aula 12, a arquitetura foi revisada sem a necessidade de adicionar novas camadas ou abstrações. A estrutura atual permanece adequada ao tamanho e ao objetivo do projeto.

## Autor

Luciano Rocha

Desenvolvedor Backend / Full Stack Júnior

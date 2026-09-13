# ServiceFlow API

API REST para gerenciamento de chamados de suporte técnico.

O projeto está sendo desenvolvido com **Java e Spring Boot**, utilizando **PostgreSQL** como banco de dados e seguindo uma estrutura voltada para boas práticas de desenvolvimento de APIs REST.

## Índice

* [Tecnologias](#tecnologias)
* [Objetivo](#objetivo)
* [Funcionalidades](#funcionalidades)
  * [Implementadas](#implementadas)
  * [Planejadas](#planejadas)
* [Estrutura do projeto](#estrutura-do-projeto)
* [Banco de dados](#banco-de-dados)
* [Executando o projeto](#executando-o-projeto)
  * [Docker Compose](#docker-compose)
  * [Testes](#testes)
  * [Perfis e configurações de ambiente](#perfis-e-configurações-de-ambiente)
  * [Build](#build)
* [Documentação da API](#documentação-da-api)
  * [Requisitos](#requisitos)
  * [Paginação e ordenação](#paginação-e-ordenação)
* [Frontend](#frontend)
* [Deploy](#deploy)
* [CI](#ci)
* [Status do projeto](#status-do-projeto)
* [Progresso do desenvolvimento](#progresso-do-desenvolvimento)
  * [Aula 1 - Configuração inicial e integração com PostgreSQL](#aula-1---configuração-inicial-e-integração-com-postgresql)
  * [Aula 2 - JPA, Hibernate e persistência de dados](#aula-2---jpa-hibernate-e-persistência-de-dados)
  * [Aula 3 - Entidade ServiceRequest e Repository](#aula-3---entidade-servicerequest-e-repository)
  * [Aula 4 - Camadas Service e Controller e primeiros endpoints REST](#aula-4---camadas-service-e-controller-e-primeiros-endpoints-rest)
  * [Aula 5 - Consulta e atualização de solicitações por ID](#aula-5---consulta-e-atualização-de-solicitações-por-id)
  * [Aula 6 - Tratamento de exceções e respostas HTTP](#aula-6---tratamento-de-exceções-e-respostas-http)
  * [Aula 7 - Validação de dados da API](#aula-7---validação-de-dados-da-api)
  * [Aula 8 - Testes dos endpoints e cobertura da API](#aula-8---testes-dos-endpoints-e-cobertura-da-api)
  * [Aula 9 - Testes unitários e integração](#aula-9---testes-unitários-e-integração)
  * [Aula 10 - DTOs e separação entre entidade e contrato da API](#aula-10---dtos-e-separação-entre-entidade-e-contrato-da-api)
  * [Aula 11 - Mapeamento entre DTOs e entidades](#aula-11---mapeamento-entre-dtos-e-entidades)
  * [Aula 12 - Organização e melhoria da arquitetura da API](#aula-12---organização-e-melhoria-da-arquitetura-da-api)
  * [Aula 13 - Alteração de status das solicitações](#aula-13---alteração-de-status-das-solicitações)
  * [Aula 14 - Regras de negócio para solicitações](#aula-14---regras-de-negócio-para-solicitações)
  * [Aula 15 - Documentação da API](#aula-15---documentação-da-api)
  * [Aula 16 - Paginação e ordenação](#aula-16---paginação-e-ordenação)
  * [Aula 17 - Testes adicionais e melhoria da cobertura](#aula-17---testes-adicionais-e-melhoria-da-cobertura)
  * [Aula 18 - Perfis e configurações de ambiente](#aula-18---perfis-e-configurações-de-ambiente)
  * [Aula 19 - Dockerização da aplicação](#aula-19---dockerização-da-aplicação)
  * [Aula 20 - Docker Compose e ambiente da aplicação](#aula-20---docker-compose-e-ambiente-da-aplicação)
  * [Aula 21 — Logs e observabilidade básica](#aula-21--logs-e-observabilidade-básica)
  * [Aula 22 — Integração com Frontend](#aula-22--integração-com-frontend)
  * [Aula 23 — Segurança da API](#aula-23--segurança-da-api)
  * [Aula 24 - Tela de login e integração com autenticação JWT](#aula-24---tela-de-login-e-integração-com-autenticação-jwt)
* [Próximas aulas](#próximas-aulas)
  * [Aula 25 — Qualidade e revisão de código](#aula-25--qualidade-e-revisão-de-código)
  * [Aula 26 — Testes e validação final](#aula-26--testes-e-validação-final)
  * [Aula 27 — Preparação para portfólio](#aula-27--preparação-para-portfólio)
  * [Aula 28 — Preparação para entrevistas](#aula-28--preparação-para-entrevistas)
* [Resumo](#resumo)
* [Autor](#autor)
* [Licença](#licença)


## Tecnologias

### Backend

* Java 21
* Spring Boot 4
* Spring Web MVC
* Spring Data JPA
* Spring Security
* Hibernate
* PostgreSQL
* Maven
* Docker
* Docker Compose
* JUnit
* Mockito
* OpenAPI
* Swagger UI
* JWT
* GitHub Actions

### Frontend

* React
* Vite
* Axios
* Bootstrap
* React Bootstrap
* React Router
* Lucide React

O frontend está integrado ao backend através da API REST e está localizado no diretório `frontend/`, dentro do mesmo repositório.

> As tecnologias foram escolhidas considerando o objetivo do projeto: construir uma API REST com uma stack comum no desenvolvimento backend corporativo, mantendo a implementação simples e adequada ao nível júnior.

## Objetivo

O **ServiceFlow** é uma aplicação web para gerenciamento de solicitações de serviço, desenvolvida com uma API REST em Java e um frontend em React.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, organização em camadas, persistência de dados, validação, tratamento de exceções, regras de negócio, testes automatizados, documentação da API, containerização, CI/CD e integração com frontend.

A aplicação permite registrar solicitações, consultar os chamados cadastrados, acompanhar seus status e navegar pelos registros através de paginação.

## Funcionalidades

### Implementadas

#### Backend

* Criação de solicitações de serviço
* Consulta de todas as solicitações
* Consulta de solicitação por ID
* Atualização de solicitações
* Alteração de status
* Validação dos dados recebidos pela API
* Tratamento global de exceções
* Regras de negócio para transição de status
* Bloqueio de alterações após os status `COMPLETED` e `CANCELLED`
* Paginação e ordenação das solicitações
* Persistência dos dados em PostgreSQL
* Documentação da API com OpenAPI e Swagger UI
* Autenticação de usuários
* Registro de usuários com senha armazenada utilizando BCrypt
* Login com geração de token JWT
* Proteção dos endpoints da API através de JWT
* Configuração de autorização através do Spring Security
* Testes automatizados de segurança
* Execução da aplicação em Docker e Docker Compose
* CI com GitHub Actions

#### Frontend

* Dashboard para visualização das solicitações
* Exibição das solicitações em páginas de 10 registros
* Navegação entre as páginas
* Exibição da quantidade total de solicitações
* Exibição da quantidade de solicitações por status na página atual
* Identificação visual dos status `PENDING`, `IN_PROGRESS`, `COMPLETED` e `CANCELLED`
* Formulário para criação de novas solicitações
* Validação básica dos campos do formulário
* Estados de carregamento durante as operações
* Tratamento de erros na comunicação com a API
* Tela de confirmação após a criação de uma solicitação
* Integração com a API REST através do Axios
* Configuração para execução local e integração com a API publicada
* Tela de login
* Integração do frontend com autenticação JWT
* Proteção das rotas do frontend para usuários autenticados
* Armazenamento do token JWT no navegador
* Logout e remoção do token de autenticação
* Redirecionamento para a tela de login quando a autenticação é inválida

### Planejadas

* Alteração de status através do frontend
* Tela de detalhes da solicitação
* Revisão final da API REST
* Melhorias de qualidade e revisão de código
* Testes e validação final
* Preparação do projeto para portfólio
* Preparação para entrevistas

## Estrutura do projeto

A aplicação segue a estrutura padrão de um projeto Spring Boot:

```text
serviceflow-api/
├── frontend/ 
│   ├── public/ 
│   │   ├── favicon.svg 
│   │   └── icons.svg 
│   ├── src/ 
│   │   ├── assets/ 
│   │   │   ├── hero.png 
│   │   │   ├── react.svg 
│   │   │   └── vite.svg 
│   │   ├── pages/ 
│   │   │   └── NewRequest.jsx 
│   │   ├── services/ 
│   │   │   └── api.js 
│   │   ├── App.css 
│   │   ├── App.jsx 
│   │   ├── index.css 
│   │   └── main.jsx 
│   ├── .gitignore 
│   ├── .oxlintrc.json 
│   ├── index.html 
│   ├── package.json 
│   ├── package-lock.json 
│   ├── README.md 
│   └── vite.config.js
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/serviceflow/api/
│   │   │   │   ├── config/
│   │   │   │   │   ├── CorsConfig.java
│   │   │   │   │   ├── OpenApiConfig.java
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   └── ServiceRequestController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── LoginResponse.java
│   │   │   │   │   ├── RegisterRequest.java
│   │   │   │   │   ├── ServiceRequestRequest.java
│   │   │   │   │   ├── ServiceRequestResponse.java
│   │   │   │   │   └── ServiceRequestStatusRequest.java
│   │   │   │   ├── entity/
│   │   │   │   │   ├── ServiceRequest.java
│   │   │   │   │   ├── ServiceRequestStatus.java
│   │   │   │   │   └── User.java
│   │   │   │   ├── exception/ 
│   │   │   │   │   ├── ErrorResponse.java
│   │   │   │   │   ├── GlobalExceptionHandler.java 
│   │   │   │   │   ├── InvalidServiceRequestStateException.java
│   │   │   │   │   └── ServiceRequestNotFoundException.java
│   │   │   │   ├── repository/
│   │   │   │   │   ├── ServiceRequestRepository.java
│   │   │   │   │   └── UserRepository.java
│   │   │   │   ├── security/
│   │   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── AuthService.java
│   │   │   │   │   ├── CustomUserDetailsService.java
│   │   │   │   │   ├── JwtService.java
│   │   │   │   │   ├── ServiceflowApiApplication.java
│   │   │   │   │   └── ServiceRequestService.java
│   │   └── resources/
│   │   │   │   ├── application.properties
│   │   │   │   └── application-dev.properties
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
│   │   └── resources/
│   │   │   └── application-test.properties
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── mvnw
├── pom.xml
└── README.md
```

## Banco de dados

O projeto utiliza PostgreSQL para persistência dos dados.

Durante o desenvolvimento, o PostgreSQL é executado em um contêiner Docker chamado `serviceflow-postgres`.

O PostgreSQL é executado através do serviço `postgres` definido no arquivo `docker-compose.yml`.

A configuração utilizada pelo ambiente Docker Compose é:

```yaml
POSTGRES_DB: serviceflow
POSTGRES_USER: serviceflow
POSTGRES_PASSWORD: serviceflow_dev
```

O PostgreSQL é exposto na porta `5432` do ambiente local:

```text
localhost:5432
```

Dentro da rede criada pelo Docker Compose, a API acessa o PostgreSQL através do nome do serviço:

```text
jdbc:postgresql://postgres:5432/serviceflow
```

O ambiente utiliza um volume externo para preservar os dados existentes do PostgreSQL mesmo após a recriação dos contêineres.

> As credenciais apresentadas acima são destinadas exclusivamente ao ambiente de desenvolvimento local. Em ambientes reais, as credenciais devem ser armazenadas de forma segura, por exemplo através de variáveis de ambiente ou mecanismos de gerenciamento de secrets.

## Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/lgomesroc/serviceflow-api.git
cd serviceflow-api
```

### Docker Compose

Na raiz do projeto, execute:

```bash
docker-compose up -d --build
```

Para verificar os containers:

```bash
docker-compose ps
```

A API ficará disponível em:

```text
http://localhost:8080
```

O PostgreSQL ficará disponível na porta `5432`.

### Frontend

Entre no diretório do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Inicie o servidor de desenvolvimento:

```bash
npm run dev
```

O frontend ficará disponível em:

```text
http://localhost:5173
```

Por padrão, o frontend utiliza a API local:

```text
http://localhost:8080/api
```

Para utilizar outra URL da API, configure a variável de ambiente `VITE_API_URL`.

### Testes

Para executar os testes do backend:

```bash
./mvnw test
```

No Windows:

```bash
mvnw.cmd test
```

### Perfis e configurações de ambiente

A aplicação possui configurações específicas para diferentes ambientes.

O arquivo `application.properties` contém as configurações gerais da aplicação, enquanto `application-dev.properties` contém configurações utilizadas durante o desenvolvimento.

A conexão com o banco de dados pode ser configurada através de variáveis de ambiente.

No frontend, a URL da API é definida através da variável `VITE_API_URL`.

### Build

Para gerar o build do backend:

```bash
./mvnw clean package
```

Para gerar o build do frontend:

```bash
cd frontend
npm run build
```

O build do frontend será gerado no diretório `frontend/dist/`.

## Documentação da API

A API REST é documentada utilizando **OpenAPI** e **Swagger UI**.

### Requisitos

Com a aplicação em execução, a documentação pode ser acessada através do Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

**OpenAPI JSON:**

http://localhost:8080/v3/api-docs

**Swagger UI — ambiente publicado:**

[https://serviceflow-api-a9jk.onrender.com/swagger-ui/index.html](https://serviceflow-api-a9jk.onrender.com/swagger-ui/index.html)

O Swagger UI permite visualizar e testar os endpoints da API diretamente pelo navegador, incluindo parâmetros, dados de requisição e respostas HTTP.

A API possui os seguintes endpoints principais:

| Método  | Endpoint                            | Descrição                          |
| ------- | ----------------------------------- | ---------------------------------- |
| `POST`  | `/api/service-requests`             | Cria uma nova solicitação          |
| `GET`   | `/api/service-requests`             | Lista as solicitações              |
| `GET`   | `/api/service-requests/{id}`        | Consulta uma solicitação por ID    |
| `PUT`   | `/api/service-requests/{id}`        | Atualiza uma solicitação           |
| `PATCH` | `/api/service-requests/{id}/status` | Altera o status de uma solicitação |

O frontend utiliza os endpoints de consulta e criação para exibir e registrar solicitações através da interface web.

### Paginação e ordenação

O endpoint `GET /api/service-requests` utiliza paginação.

Parâmetros disponíveis:

* `page` — número da página, iniciando em `0`
* `size` — quantidade de registros por página
* `sort` — campo e direção da ordenação

Exemplo:

```text
GET /api/service-requests?page=0&size=10
```

Por padrão, as solicitações são ordenadas por `createdAt` em ordem decrescente.

A resposta contém as informações da página atual, incluindo:

* `content`
* `number`
* `size`
* `numberOfElements`
* `totalElements`
* `totalPages`

O frontend utiliza essa paginação para exibir as solicitações em páginas de 10 registros.

## Frontend

O frontend do ServiceFlow foi desenvolvido em **React** utilizando **Vite** e está localizado no diretório `frontend/`, dentro do mesmo repositório da API.

A aplicação se comunica com o backend através da API REST utilizando **Axios**.

### Funcionalidades atuais

* Dashboard com listagem das solicitações
* Paginação com 10 solicitações por página
* Exibição do total de solicitações
* Contagem das solicitações por status na página atual
* Identificação visual dos diferentes status
* Formulário para criação de solicitações
* Validação básica dos campos
* Indicador de carregamento durante as requisições
* Tratamento de erros de comunicação com a API
* Tela de confirmação após a criação de uma solicitação

### Tecnologias utilizadas

* React
* Vite
* Axios
* Bootstrap
* React Bootstrap
* React Router
* Lucide React

### Execução local

Na raiz do projeto frontend:

```bash id="q9j1fb"
cd frontend
npm install
npm run dev
```

O frontend será disponibilizado pelo servidor de desenvolvimento do Vite.

A URL da API utilizada pelo frontend pode ser configurada através da variável de ambiente `VITE_API_URL`.

### Integração com a API

Em ambiente local, o frontend utiliza a API disponível em:

```text id="x5o6gd"
http://localhost:8080/api
```

Em produção, a URL da API é configurada através da variável `VITE_API_URL`.

A comunicação entre frontend e backend também foi configurada com CORS para permitir as origens utilizadas no desenvolvimento local e no ambiente de produção.

### Deploy

O frontend está publicado como um **Static Site no Render**.

A aplicação em produção consome a API publicada no Render e permite consultar e criar solicitações diretamente pela interface web.

## Deploy

A aplicação está publicada em ambiente de produção utilizando **Render** para hospedagem da API e do frontend, com **Neon** como banco de dados PostgreSQL.

### Backend

A API Spring Boot está hospedada no Render.

**API em produção:**

https://serviceflow-api-a9jk.onrender.com

**Swagger UI:**

https://serviceflow-api-a9jk.onrender.com/swagger-ui/index.html

A aplicação utiliza variáveis de ambiente para configurar a conexão com o banco de dados PostgreSQL disponibilizado pelo Neon.

### Banco de dados

O PostgreSQL de produção é hospedado no Neon.

A aplicação utiliza as credenciais e configurações de conexão através de variáveis de ambiente, evitando deixar informações sensíveis diretamente no código-fonte.

### Frontend

O frontend React está publicado no Render como **Static Site**.

**Frontend em produção:**

https://serviceflow-frontend-bmyj.onrender.com

O frontend utiliza a variável de ambiente `VITE_API_URL` para definir o endereço da API que será consumida em produção.

### Integração em produção

A comunicação entre os serviços ocorre da seguinte forma:

```text
Usuário
   ↓
Frontend React
   ↓
API REST Spring Boot
   ↓
PostgreSQL (Neon)
```

O backend possui configuração de CORS permitindo a comunicação entre o frontend publicado e a API.

A integração foi validada em produção através da consulta e criação de solicitações pelo frontend.

### Infraestrutura

- **Render** — hospedagem da aplicação Spring Boot
- **Neon** — PostgreSQL em ambiente cloud
- **Docker** — utilizado na construção e execução da aplicação
- **Java 21** — runtime da aplicação

O deploy utiliza variáveis de ambiente para configurar a conexão com o PostgreSQL, mantendo as credenciais do banco fora do código-fonte.

A aplicação publicada utiliza o perfil `dev` e se conecta ao PostgreSQL hospedado no Neon.

O ambiente publicado foi validado através do Swagger UI, incluindo:

- Consulta de solicitações de serviço
- Criação de solicitações
- Persistência dos dados no PostgreSQL
- Consulta dos dados persistidos
- Comunicação entre a API publicada e o banco PostgreSQL

## CI

O projeto utiliza **GitHub Actions** para automação de integração contínua.

O workflow de CI é executado a partir do repositório e realiza as etapas necessárias para validar o projeto, incluindo:

* Configuração do ambiente Java
* Instalação das dependências
* Execução dos testes automatizados
* Build da aplicação
* Geração do artefato de build

O CI ajuda a garantir que alterações submetidas ao projeto sejam verificadas automaticamente antes de serem integradas à branch principal.

O frontend faz parte do mesmo repositório, mas o workflow atual de CI está direcionado à validação do backend Java.

### Execução dos testes

O workflow de CI está definido em:

```text
.github/workflows/ci.yml
```

Durante a execução, o GitHub Actions:

* Utiliza Java 21 através do Temurin.
* Utiliza o Maven Wrapper do projeto.
* Inicia temporariamente um contêiner PostgreSQL para os testes.
* Executa a suíte completa de testes através do Maven.
* Considera a execução concluída com sucesso somente quando os testes são aprovados.

O comando utilizado pelo workflow é:

```bash
./mvnw clean test
```

### PostgreSQL no GitHub Actions

Os testes de integração do projeto dependem de um banco PostgreSQL.

No ambiente do GitHub Actions, o PostgreSQL é executado **temporariamente como um serviço do próprio ambiente de CI**.

Esse banco não é o mesmo PostgreSQL utilizado no desenvolvimento local, nem o PostgreSQL utilizado pelo ambiente publicado no Render.

A estrutura utilizada pelo CI é:

```text
GitHub Actions
      │
      ├── Java 21
      │
      ├── PostgreSQL temporário
      │
      └── ./mvnw clean test
```

O PostgreSQL utilizado pelo GitHub Actions existe somente durante a execução do workflow e é descartado ao final da execução.

As credenciais utilizadas pelo ambiente de testes do CI são as mesmas definidas em `src/test/resources/application-test.properties`, permitindo que a suíte de testes seja executada sem depender de um banco de dados externo.

### Pull Requests

As alterações destinadas à branch `main` devem ser realizadas através de Pull Requests.

O fluxo utilizado pelo projeto é:

```text
Branch de desenvolvimento
          ↓
    Pull Request
          ↓
     GitHub Actions
          ↓
   PostgreSQL temporário
          ↓
      45 testes
          ↓
       Sucesso
          ↓
      Merge na main
```

Após o merge na `main`, o **Render** realiza automaticamente o deploy da nova versão da aplicação.

Dessa forma, o projeto utiliza:

* **CI — GitHub Actions:** validação automática dos testes.
* **CD — Render:** publicação automática da aplicação após alterações na `main`.

O ambiente de CI e o ambiente de produção são independentes. O PostgreSQL temporário utilizado pelo GitHub Actions não possui relação com o banco PostgreSQL hospedado no Neon utilizado pela aplicação publicada.

## Status do projeto

Em desenvolvimento.

O projeto está sendo desenvolvido de forma incremental, evoluindo de uma API REST básica para uma aplicação com persistência em PostgreSQL, validação de dados, tratamento de exceções, regras de negócio, testes automatizados, documentação com OpenAPI/Swagger, paginação e ordenação dos resultados.

Até o momento, foram concluídas **24 aulas** do desenvolvimento do ServiceFlow.

A API REST está funcional, testada, documentada e publicada em produção.

O frontend em React também está integrado à API, possui dashboard, paginação e criação de solicitações, e está publicado em produção.

A integração entre frontend, backend e banco de dados foi validada em ambiente de produção.

As próximas etapas estão relacionadas principalmente à segurança, revisão, qualidade, testes finais e preparação do projeto para portfólio e entrevistas.


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

### Aula 17 - Testes adicionais e melhoria da cobertura

- Revisão da suíte de testes existente.
- Identificação de cenários adicionais das regras de negócio que poderiam ser protegidos por testes.
- Ampliação dos testes unitários da camada Service.
- Cobertura das transições de status permitidas entre `PENDING`, `IN_PROGRESS`, `COMPLETED` e `CANCELLED`.
- Cobertura das transições de status não permitidas.
- Validação de que solicitações em `COMPLETED` não podem ter o status alterado.
- Validação de que solicitações em `CANCELLED` não podem ter o status alterado.
- Validação de que solicitações em estados finais não podem ter seus dados atualizados.
- Validação do comportamento para atualização de solicitação inexistente.
- Validação do comportamento para alteração de status de solicitação inexistente.
- Revisão dos testes de Controller para os principais cenários de sucesso, validação e erro HTTP.
- Cobertura dos cenários de `400 Bad Request`, `404 Not Found` e `409 Conflict`.
- Revisão dos testes existentes para evitar cobertura desnecessariamente duplicada.
- Remoção da operação de exclusão de solicitações de serviço.
- Definição de que a API não disponibiliza `DELETE` para solicitações, preservando o histórico dos chamados para consultas futuras e auditoria.
- Confirmação de que solicitações concluídas ou canceladas permanecem armazenadas e não podem ser alteradas.
- Execução da suíte completa de testes com sucesso: **45 testes, 0 falhas, 0 erros**.
- Confirmação do `BUILD SUCCESS`.

### Aula 18 - Perfis e configurações de ambiente

- Introdução aos perfis de configuração do Spring Boot.
- Separação das configurações de desenvolvimento e teste.
- Criação do arquivo `application-dev.properties`.
- Criação do arquivo `application-test.properties`.
- Manutenção das configurações comuns no `application.properties`.
- Configuração do perfil `dev` para execução da aplicação.
- Configuração do perfil `test` para execução dos testes.
- Utilização de `@ActiveProfiles("test")` nos testes que carregam o contexto do Spring.
- Utilização de variáveis de ambiente para usuário e senha do PostgreSQL.
- Validação da execução da aplicação utilizando o perfil `dev`.
- Validação da execução dos testes utilizando o perfil `test`.
- Criação do `.env.example` para documentar as variáveis utilizadas pelo projeto.
- Manutenção do `.env` apenas no ambiente local, sem versionamento no Git.
- Validação de que o perfil `dev` não é carregado automaticamente durante os testes.
- Validação de que o perfil `test` é carregado especificamente pelos testes.
- Execução da suíte completa de testes com sucesso: **45 testes, 0 falhas e 0 erros**.
- Confirmação do `BUILD SUCCESS`.

### Aula 19 - Dockerização da aplicação

- Introdução à execução da aplicação Spring Boot em Docker.
- Criação do `Dockerfile`.
- Utilização do JAR da aplicação na construção da imagem Docker.
- Criação da imagem Docker da API.
- Execução da API em um contêiner Docker.
- Criação da rede Docker `serviceflow-network`.
- Conexão do contêiner PostgreSQL à rede Docker.
- Configuração da comunicação entre a API e o PostgreSQL através da rede Docker.
- Utilização do nome do contêiner `serviceflow-postgres` como host do PostgreSQL.
- Configuração das variáveis de ambiente para execução da API.
- Ativação do perfil dev no contêiner da API.
- Execução da API através do `docker run`.
- Validação da comunicação entre os contêineres.
- Validação do endpoint `GET /api/service-requests` com a API executando em Docker.
- Validação da persistência dos dados no PostgreSQL.
- Validação dos logs da aplicação através do Docker.
- Validação do Swagger UI com a API executando em Docker.
- Execução da suíte completa de testes com sucesso: **45 testes, 0 falhas e 0 erros**.
- Confirmação do `BUILD SUCCESS`.

### Aula 20 - Docker Compose e ambiente da aplicação

- Introdução ao Docker Compose.
- Criação do `docker-compose.yml`.
- Configuração do serviço da API.
- Configuração do serviço PostgreSQL.
- Configuração da comunicação entre os contêineres através da rede criada pelo Docker Compose.
- Utilização do nome do serviço `postgres` como host do PostgreSQL dentro do ambiente Docker Compose.
- Configuração das variáveis de ambiente da API.
- Configuração de volume externo para preservação dos dados existentes do PostgreSQL.
- Migração do ambiente executado manualmente com `docker run` para Docker Compose.
- Inicialização da API e do PostgreSQL através do `docker-compose up`.
- Validação dos contêineres através do `docker-compose ps`.
- Validação dos logs da aplicação através do `docker-compose logs`.
- Validação da comunicação entre a API e o PostgreSQL.
- Validação da preservação dos dados existentes no PostgreSQL.
- Criação de uma nova solicitação através do Swagger UI com a API executando em Docker Compose.
- Validação da persistência da nova solicitação diretamente no PostgreSQL.
- Correção da configuração do perfil de testes para execução local através do Maven.
- Diferenciação entre `localhost:5432`, utilizado pelos testes executados no host, e `postgres:5432`, utilizado pela API dentro do Docker Compose.
- Execução da suíte completa de testes com sucesso: **45 testes, 0 falhas e 0 erros**.
- Confirmação do `BUILD SUCCESS`.

### Aula 21 — Logs e observabilidade básica

- Introdução aos conceitos básicos de logs e observabilidade.
- Diferenciação entre os níveis de log `INFO`, `WARN` e `ERROR`, além da identificação de registros `FATAL` nos logs do PostgreSQL.
- Análise dos logs gerados pelo Spring Boot durante a inicialização da aplicação.
- Análise dos logs do Tomcat durante a inicialização do servidor HTTP.
- Análise dos logs do HikariCP durante a criação da conexão com o PostgreSQL.
- Análise dos logs do Hibernate durante a inicialização do JPA.
- Análise dos logs do SpringDoc durante a inicialização da documentação OpenAPI.
- Utilização do `docker-compose logs` para visualizar os logs dos contêineres.
- Utilização do `docker-compose logs -f` para acompanhar os logs da API em tempo real.
- Utilização de filtros com `grep` para localizar logs específicos.
- Análise dos logs de `WARN` gerados pelo Spring Boot e SpringDoc.
- Identificação do aviso relacionado ao `spring.jpa.open-in-view`.
- Identificação dos avisos relacionados aos endpoints do SpringDoc.
- Identificação do aviso relacionado à serialização de objetos `PageImpl`.
- Análise de uma resposta `404 Not Found` para uma solicitação inexistente.
- Análise de uma resposta `409 Conflict` para uma transição de status inválida.
- Análise dos logs do PostgreSQL.
- Identificação de um registro histórico de falha de autenticação no PostgreSQL.
- Diferenciação entre uma falha de autenticação registrada como `FATAL` e uma falha do próprio banco de dados.
- Verificação da ausência de logs `ERROR` na execução atual da API.
- Verificação da inexistência de logs explícitos da aplicação antes da implementação.
- Identificação da infraestrutura de logging já disponibilizada pelo Spring Boot através das dependências existentes.
- Implementação de logging próprio na camada `ServiceRequestService` utilizando SLF4J.
- Criação do `Logger` através de `LoggerFactory`.
- Adição de log `INFO` após a criação de uma solicitação de serviço.
- Adição de log `INFO` após uma alteração de status realizada com sucesso.
- Registro do ID da solicitação, status anterior e novo status durante a alteração.
- Adição de log `WARN` para tentativa de alteração de uma solicitação em estado final.
- Adição de log `WARN` para tentativa de realizar uma transição de status inválida.
- Validação prática do log de criação da solicitação.
- Validação prática do log de alteração de status de `PENDING` para `IN_PROGRESS`.
- Validação prática do log de uma tentativa de transição inválida de `IN_PROGRESS` para `PENDING`.
- Validação do retorno HTTP `409 Conflict` para a transição inválida.
- Validação dos logs gerados pela aplicação diretamente através do Docker.
- Execução da suíte completa de testes com sucesso: **45 testes, 0 falhas e 0 erros**.
- Confirmação do `BUILD SUCCESS`.

### Aula 22 — Integração com Frontend

Nesta aula, foi desenvolvido e integrado o frontend do ServiceFlow utilizando React e Vite, mantendo o frontend dentro do mesmo repositório da API.

Principais atividades realizadas:

* Criação do projeto frontend com React e Vite
* Organização do frontend dentro do diretório `frontend/`
* Configuração das dependências necessárias para a interface
* Integração com a API REST utilizando Axios
* Configuração da URL da API através da variável de ambiente `VITE_API_URL`
* Implementação do dashboard de solicitações
* Consumo do endpoint de listagem de solicitações
* Implementação de paginação com 10 registros por página
* Exibição do total de solicitações
* Exibição da quantidade de solicitações por status na página atual
* Identificação visual dos status `PENDING`, `IN_PROGRESS`, `COMPLETED` e `CANCELLED`
* Implementação do formulário para criação de novas solicitações
* Validação básica dos campos do formulário
* Tratamento dos estados de carregamento e erro
* Implementação da tela de confirmação após a criação de uma solicitação
* Configuração de CORS no backend para permitir a comunicação com o frontend
* Configuração da integração entre frontend e backend em ambiente local
* Publicação do frontend como Static Site no Render
* Configuração da comunicação entre o frontend publicado e a API publicada
* Validação da aplicação completa em produção, incluindo consulta e criação de solicitações

Nesta etapa, não foi implementada a alteração de status através do frontend nem uma tela de detalhes da solicitação. Essas funcionalidades permanecem planejadas para etapas posteriores.

### Aula 23 — Segurança da API

* Introdução a autenticação e autorização
* Spring Security
* Configuração do `SecurityFilterChain`
* API stateless
* Criação da entidade `User`
* `UserRepository`
* Cadastro de usuários com `POST /auth/register`
* Criptografia de senhas com BCrypt
* Login com `POST /auth/login`
* Geração e validação de tokens JWT
* Implementação do `JwtAuthenticationFilter`
* Autenticação através do `SecurityContext`
* Implementação do `CustomUserDetailsService`
* Proteção dos endpoints `/api/**`
* Liberação dos endpoints de autenticação e documentação
* Configuração do Swagger para autenticação Bearer JWT
* Testes automatizados de segurança
* Execução da suíte completa com **48 testes**, sem falhas ou erros
* Build final concluído com sucesso

### Aula 24 — Tela de login e integração com autenticação JWT

* Implementação da tela de login no frontend.
* Integração do frontend com o endpoint `POST /auth/login`.
* Armazenamento do token JWT no `localStorage`.
* Criação de rota protegida para as páginas autenticadas.
* Redirecionamento para a tela de login quando não existe token.
* Inclusão automática do JWT nas requisições da API através de interceptor do Axios.
* Implementação de logout no frontend.
* Integração entre frontend publicado e API publicada.
* Configuração de CORS para permitir a comunicação entre frontend e API.
* Liberação das requisições `OPTIONS` para funcionamento do preflight CORS.
* Validação do fluxo completo de autenticação no frontend.
* Validação do acesso às solicitações após autenticação.
* Execução da suíte completa de testes com sucesso: **48 testes, 0 falhas e 0 erros**.
* Confirmação do `BUILD SUCCESS`.

## Próximas aulas

### Aula 25 — Qualidade e revisão de código

* Revisar a organização do projeto
* Revisar responsabilidades das classes
* Revisar princípios SOLID
* Identificar duplicações e código desnecessário
* Avaliar possíveis melhorias sem adicionar complexidade desnecessária
* Realizar uma revisão geral do código

### Aula 26 — Testes e validação final

* Executar a suíte completa de testes
* Validar os endpoints da API
* Validar a aplicação utilizando Docker Compose
* Validar a comunicação com o PostgreSQL
* Validar a documentação da API
* Corrigir eventuais problemas encontrados na validação final

### Aula 27 — Preparação para portfólio

* Revisar o README
* Revisar a estrutura do repositório
* Documentar tecnologias e funcionalidades
* Documentar a execução do projeto
* Documentar Docker e Docker Compose
* Apresentar testes e documentação da API
* Preparar o projeto para apresentação no GitHub

### Aula 28 — Preparação para entrevistas

* Aprender a apresentar o ServiceFlow API
* Explicar a arquitetura do projeto
* Explicar as principais decisões técnicas
* Revisar perguntas sobre Java e Spring Boot
* Revisar perguntas sobre JPA e Hibernate
* Revisar perguntas sobre testes
* Revisar perguntas sobre Docker
* Revisar perguntas sobre API REST
* Explicar regras de negócio e decisões do projeto

> **Observação:** a Aula 28 faz parte da trilha de desenvolvimento do projeto e permanecerá no README durante o desenvolvimento. Na documentação final do projeto, esta aula não será incluída.


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
✓ Aula 15 → Documentação da API com Swagger/OpenAPI<br>
✓ Aula 16 → Paginação e ordenação<br>
✓ Aula 17 → Testes adicionais e melhoria da cobertura<br>
✓ Aula 18 → Perfis e configurações de ambiente<br>
✓ Aula 19 → Dockerização da aplicação<br>
✓ Aula 20 → Docker Compose e ambiente da aplicação<br>
✓ Aula 21 → Logs e observabilidade básica<br>
✓ Aula 22 → Integração com Frontend<br>
✓ Aula 23 → Segurança da API<br>
✓ Aula 24 — Tela de login e integração com autenticação JWT<br>
- [ ] Aula 25 — Qualidade e revisão de código
- [ ] Aula 26 — Testes e validação final
- [ ] Aula 27 — Preparação para portfólio
- [ ] Aula 28 — Preparação para entrevistas

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

> Na aula 17, a API não disponibiliza uma operação de exclusão (`DELETE`) para solicitações de serviço.

> Os chamados devem permanecer armazenados para preservar o histórico das solicitações e permitir consultas futuras e auditoria.

> Solicitações concluídas ou canceladas também permanecem armazenadas e não podem ser alteradas.

## Autor

Luciano Rocha

Desenvolvedor Backend / Full Stack Júnior

## Licença

Este projeto está licenciado sob a **MIT License**.

Consulte o arquivo [`LICENSE`](./LICENSE) para obter o texto completo da licença.

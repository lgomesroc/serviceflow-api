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
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
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

Inicie o PostgreSQL:

```bash
docker run --name serviceflow-postgres \
  -e POSTGRES_DB=serviceflow \
  -e POSTGRES_USER=serviceflow \
  -e POSTGRES_PASSWORD=serviceflow_dev \
  -p 5432:5432 \
  -d postgres
```

Execute os testes:

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

## Autor

Luciano Rocha

Desenvolvedor Backend / Full Stack Júnior

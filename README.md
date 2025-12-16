# 📋 API de Cadastro de Usuários e Tarefas

Uma API REST completa desenvolvida em Java com Spring Boot para gerenciamento de usuários e suas respectivas tarefas.

## 🚀 Visão Geral

Esta API oferece funcionalidades completas para:

- **Gerenciamento de Usuários**: CRUD completo com validações
- **Gerenciamento de Tarefas**: Sistema de tarefas vinculadas aos usuários
- **Documentação Interativa**: Swagger UI integrado
- **Banco de Dados**: H2 em memória para desenvolvimento

## 🛠️ Tecnologias Utilizadas

### Backend

- **Java 24** - Linguagem de programação
- **Spring Boot 3.5.6** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Spring Validation** - Validação de dados
- **Spring Actuator** - Monitoramento da aplicação

### Banco de Dados

- **H2 Database** - Banco em memória para desenvolvimento
- **Hibernate** - ORM (Object-Relational Mapping)

### Documentação

- **SpringDoc OpenAPI 3** - Documentação automática da API
- **Swagger UI** - Interface interativa para testes

### Ferramentas de Desenvolvimento

- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **Spring Boot DevTools** - Recarga automática durante desenvolvimento

## 🏗️ Arquitetura e Práticas Adotadas

### Padrões de Arquitetura

- **MVC (Model-View-Controller)** - Separação de responsabilidades
- **Repository Pattern** - Abstração da camada de dados
- **Service Layer** - Lógica de negócio centralizada
- **DTO Pattern** - Transferência de dados entre camadas



## 🔗 Endpoints da API

### Usuários (`/api/usuarios`)

| Método | Endpoint                      | Descrição                |
| ------ | ----------------------------- | ------------------------ |
| POST   | `/api/usuarios`               | Criar novo usuário       |
| GET    | `/api/usuarios`               | Listar todos os usuários |
| GET    | `/api/usuarios/{id}`          | Buscar usuário por ID    |
| GET    | `/api/usuarios/email/{email}` | Buscar usuário por email |
| PUT    | `/api/usuarios/{id}`          | Atualizar usuário        |
| DELETE | `/api/usuarios/{id}`          | Deletar usuário          |
| GET    | `/api/usuarios/hello`         | Endpoint de teste        |

### Tarefas (`/api/tarefas`)

| Método | Endpoint            | Descrição               |
| ------ | ------------------- | ----------------------- |
| POST   | `/api/tarefas`      | Criar nova tarefa       |
| GET    | `/api/tarefas`      | Listar todas as tarefas |
| PUT    | `/api/tarefas`      | Atualizar tarefa        |
| DELETE | `/api/tarefas/{id}` | Deletar tarefa          |

### Monitoramento

| Método | Endpoint           | Descrição           |
| ------ | ------------------ | ------------------- |
| GET    | `/actuator/health` | Status da aplicação |

## 🚀 Instalação e Configuração

### Pré-requisitos

- **Java 24** ou superior
- **Maven 3.6+**
- **Git**

### Passos para Instalação

1. **Clone o repositório**

```bash
git clone <url-do-repositorio>
cd cadastrodeclientes
```

2. **Compile o projeto**

```bash
mvn clean compile
```

3. **Execute a aplicação**

```bash
mvn spring-boot:run
```

4. **Acesse a aplicação**

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- H2 Console: `http://localhost:8080/h2-console`

### Configurações do Banco de Dados

A aplicação está configurada para usar H2 em memória:

- **URL**: `jdbc:h2:mem:testdb`
- **Usuário**: `sa`
- **Senha**: `password`

## 🧪 Como Testar a API

### 1. Via Swagger UI (Recomendado)

1. Acesse: `http://localhost:8080/swagger-ui.html`
2. Explore os endpoints disponíveis
3. Use a interface interativa para testar as requisições
4. Visualize a documentação completa da API

### 2. Via cURL

#### Criar um usuário

```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@email.com",
    "senha": "123456"
  }'
```

#### Listar usuários

```bash
curl -X GET http://localhost:8080/api/usuarios
```

#### Criar uma tarefa

```bash
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Estudar Spring Boot",
    "descricao": "Aprender conceitos básicos do Spring Boot",
    "prioridade": 1,
    "realizado": false,
    "usuario": {"id": 1}
  }'
```

### 3. Via Postman/Insomnia

Importe a coleção usando a documentação OpenAPI disponível em:
`http://localhost:8080/v3/api-docs`

### 4. Teste de Saúde da Aplicação

```bash
curl -X GET http://localhost:8080/actuator/health
```

## 📝 Exemplos de Uso

### Criando um Usuário

```json
POST /api/usuarios
{
  "nome": "Maria Santos",
  "email": "maria@email.com",
  "senha": "senha123"
}
```

### Resposta

```json
{
  "id": 1,
  "nome": "Maria Santos",
  "email": "maria@email.com"
}
```

### Criando uma Tarefa

```json
POST /api/tarefas
{
  "titulo": "Implementar autenticação",
  "descricao": "Adicionar sistema de login e JWT",
  "prioridade": 2,
  "realizado": false,
  "usuario": {"id": 1}
}
```

## 🔧 Configurações Avançadas

### Alterando a Porta

Adicione no `application.properties`:

```properties
server.port=8081
```

### Configurando Banco de Dados Externo

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cadastro
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

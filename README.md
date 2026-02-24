# Cadastro de Usuários
![Java](https://img.shields.io/badge/Java-262626.svg??style=for-the-badge&logo=openjdk&logoColor=white)&nbsp;
![Spring Boot](https://img.shields.io/badge/Spring_Boot-262626?style=flat&logo=Spring)&nbsp;
![HTML](https://img.shields.io/badge/-HTML-262626?style=flat&logo=HTML5)&nbsp;
![CSS](https://img.shields.io/badge/CSS-262626?logo=css&logoColor=43a8d4)&nbsp;
![Flyway](https://img.shields.io/badge/-Flyway-262626?style=flat&logo=flyway)&nbsp;
![Thymeleaf](https://img.shields.io/badge/-Thymeleaf-262626?style=flat&logo=Thymeleaf)&nbsp;
![JWT](https://img.shields.io/badge/-JWT-262626?style=flat&logo=JSON)&nbsp;


Sistema de cadastro de Usuários desenvolvido com Java e Spring Boot, incluindo funcionalidades de gerenciamento de tarefas e autenticação. 
Esse projeto permite que o Usuário se cadastre, faça autentificação e acesse a interface web para gerenciar as suas tarefas.

## Tecnologias Utilizadas

- **Java 24**
- **Spring Boot 3.5.6**
- **Spring Data JPA** - Para persistência de dados
- **Spring Security** - Para autenticação e autorização
- **Spring Web** - Para desenvolvimento de APIs REST
- **Thymeleaf** - Para renderização de páginas HTML
- **H2 Database** - Banco de dados em memória
- **Flyway** - Para migrações de banco de dados
- **JWT (JSON Web Token)** - Para autenticação via tokens
- **Lombok** - Para redução de código boilerplate
- **SpringDoc OpenAPI** - Para documentação da API
- **Spring Boot Actuator** - Para monitoramento e gestão

## Pré-requisitos

- Java 24 ou superior
- Maven 3.6 ou superior

## Como Instalar e Executar

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd cadastrodeclientes
   ```

2. **Compile o projeto:**
   ```bash
   ./mvnw clean install
   ```

3. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse a aplicação:**
   - Interface web: http://localhost:8080
   - Documentação da API (Swagger): http://localhost:8080/swagger-ui.html
   - Console H2: http://localhost:8080/h2-console

## Funcionalidades Principais

### Usuários
- Cadastro: Nome, E-mail, Senha, Idade e Papel (ROLE_USER/ROLE_ADMIN).
- Autenticação: Login com geração de token JWT.
- Gerenciamento: Listagem, busca por ID, alteração e exclusão de usuários.
- Segurança: Senhas criptografadas com BCrypt e controle de acesso por roles.

### Tarefas
- Cadastro: Título, Descrição, Status (PENDENTE/EM_ANDAMENTO/CONCLUIDA) e Data de criação.
- Listagem: Visualização de todas as tarefas cadastradas.
- Gerenciamento: Busca por ID, alteração e exclusão de tarefas.
- Associação: Tarefas vinculadas a usuários autenticados.

### Autenticação
- Login: Autenticação via e-mail e senha com geração de token JWT.
- Refresh: Renovação de token de acesso.
- Logout: Encerramento de sessão.
- Perfil: Visualização de dados do usuário autenticado.

## Endpoints da API

### 1. Usuários (/usuario)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/usuario/boasvindas` | Retorna mensagem de boas-vindas |
| POST | `/usuario/adicionar` | Cadastra um novo usuário |
| GET | `/usuario/listar` | Lista todos os usuários cadastrados |
| GET | `/usuario/listar/{id}` | Busca usuário por ID específico |
| PUT | `/usuario/alterar/{id}` | Altera dados de um usuário por ID |
| DELETE | `/usuario/deletar/{id}` | Remove um usuário por ID |

### 2. Tarefas (/tarefa)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/tarefa/adicionar` | Cadastra uma nova tarefa |
| GET | `/tarefa/listar` | Lista todas as tarefas cadastradas |
| GET | `/tarefa/listar/{id}` | Busca tarefa por ID específico |
| PUT | `/tarefa/alterar/{id}` | Altera dados de uma tarefa por ID |
| DELETE | `/tarefa/deletar/{id}` | Remove uma tarefa por ID |

### 3. Autenticação (/auth)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/login` | Realiza login e gera token JWT |
| POST | `/auth/refresh` | Renova o token de acesso |
| GET | `/auth/me` | Retorna dados do usuário autenticado |
| POST | `/auth/logout` | Encerra a sessão do usuário |

## Configuração

O projeto utiliza um arquivo `.env` para configurações de ambiente (como chaves JWT). Certifique-se de configurar as variáveis de ambiente necessárias antes de executar a aplicação.
- <b>Variáveis de Ambiente</b>: `DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD, JWT_SECRET, JWT_EXPIRATION`

## Estrutura do Projeto

- `src/main/java/dev/nicolas/cadastrodeusuarios/` - Código fonte principal
- `src/main/resources/templates/` - Templates Thymeleaf
- `src/main/resources/static/` - Arquivos estáticos (CSS, JS)
- `src/main/resources/data/` - Dados iniciais do banco
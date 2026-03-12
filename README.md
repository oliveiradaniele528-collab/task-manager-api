Task Manager API

API REST para gerenciamento de tarefas (To-Do List), desenvolvida como desafio técnico backend.

A aplicação permite criar, listar, atualizar, deletar e filtrar tarefas, aplicando regras de negócio e validações.

--------------------------------------------------

TECNOLOGIAS UTILIZADAS

- Java 17
- Spring Boot
- MongoDB Atlas (NoSQL)
- Maven
- Postman (testes)

--------------------------------------------------

ARQUITETURA

O projeto foi organizado em camadas:

Controller
Responsável por expor os endpoints REST.

Service
Responsável pelas regras de negócio e validações.

Repository
Responsável pelo acesso ao banco de dados MongoDB.

Exception Handler
Responsável pelo tratamento global de erros da API.

--------------------------------------------------

MODELO DE DADOS

{
  "id": "string",
  "title": "string",
  "description": "string",
  "status": "PENDING | IN_PROGRESS | COMPLETED | CANCELLED",
  "priority": "LOW | MEDIUM | HIGH",
  "dueDate": "yyyy-MM-dd",
  "createdAt": "timestamp",
  "updatedAt": "timestamp"
}

--------------------------------------------------

REGRAS DE NEGÓCIO

- Título obrigatório (3 a 100 caracteres)
- Prioridade obrigatória
- Status deve ser válido
- Data de vencimento não pode estar no passado
- Tarefas com status COMPLETED não podem ser editadas
- IDs gerados de forma sequencial no MongoDB

--------------------------------------------------

ENDPOINTS

Criar tarefa
POST /tasks

Listar tarefas
GET /tasks

Filtrar por status
GET /tasks?status=PENDING

Filtrar por prioridade
GET /tasks?priority=HIGH

Buscar por ID
GET /tasks/{id}

Atualizar tarefa
PUT /tasks/{id}

Deletar tarefa
DELETE /tasks/{id}

--------------------------------------------------

EXEMPLO DE CRIAÇÃO

{
  "title": "Estudar Spring Boot",
  "description": "Revisar controllers",
  "priority": "HIGH",
  "dueDate": "2026-04-01"
}

--------------------------------------------------

EXEMPLO DE ERRO

{
  "timestamp": "2026-03-11T20:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Título é obrigatório",
  "path": "/tasks",
  "method": "POST"
}

--------------------------------------------------

COMO RODAR O PROJETO

1. Clonar o repositório

git clone https://github.com/oliveiradaniele528-collab/task-manager-api.git


2. Configurar MongoDB

Criar o arquivo:

src/main/resources/application.properties

Adicionar:

spring.data.mongodb.uri=<sua_connection_string>
server.port=8080


3. Rodar a aplicação

mvn spring-boot:run

ou rodar a classe TaskmanagerApplication no IntelliJ.

--------------------------------------------------

TESTES REALIZADOS

- CRUD completo
- filtros por status e prioridade
- validações obrigatórias
- bloqueio de edição de tarefas COMPLETED
- enum inválido tratado
- recurso não encontrado
- JSON inválido tratado

--------------------------------------------------

OBSERVAÇÃO

O desafio indicava Golang como linguagem preferencial, porém permitia outras linguagens backend.

Foi escolhida a stack Java + Spring Boot visando garantir uma implementação sólida com foco em boas práticas e organização arquitetural.

--------------------------------------------------

MELHORIAS FUTURAS

- Swagger / OpenAPI
- Testes unitários
- Docker
- Logs estruturados
- Paginação

--------------------------------------------------

Autor

Daniele Oliveira

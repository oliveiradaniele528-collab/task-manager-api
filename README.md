# 📝 Task Manager API

API REST para gerenciamento de tarefas (To-Do List), desenvolvida como desafio técnico backend.

A aplicação permite criar, listar, atualizar, deletar e filtrar tarefas, aplicando regras de negócio e validações.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- MongoDB Atlas (NoSQL)
- Maven
- Postman (testes)

---

## 🧱 Arquitetura

O projeto foi organizado em camadas:

- **Controller** → exposição dos endpoints REST  
- **Service** → regras de negócio e validações  
- **Repository** → acesso ao banco MongoDB  
- **Exception Handler** → tratamento global de erros  

---

## 📦 Modelo de Dados

```json
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
⚙️ Regras de negócio

Título obrigatório (3 a 100 caracteres)

Prioridade obrigatória

Status deve ser válido

Data de vencimento não pode ser no passado

Tarefas com status COMPLETED não podem ser editadas

IDs gerados de forma sequencial no MongoDB

🔌 Endpoints
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
🧪 Exemplo de criação
{
  "title": "Estudar Spring Boot",
  "description": "Revisar controllers",
  "priority": "HIGH",
  "dueDate": "2026-04-01"
}
❗ Exemplo de erro
{
  "timestamp": "2026-03-11T20:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Título é obrigatório",
  "path": "/tasks",
  "method": "POST"
}
▶️ Como rodar o projeto
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

🧪 Testes realizados

CRUD completo

filtros por status e prioridade

validações obrigatórias

bloqueio de edição de tarefas COMPLETED

enum inválido tratado

recurso não encontrado

JSON inválido tratado

💡 Observação

O desafio indicava Golang como linguagem preferencial, porém permitia outras linguagens backend.

Foi escolhida a stack Java + Spring Boot visando garantir uma implementação sólida com foco em boas práticas e organização arquitetural.

📊 Melhorias futuras

Swagger / OpenAPI

Testes unitários

Docker

Logs estruturados

Paginação

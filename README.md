# 📝 Task Manager API

API REST para gerenciamento de tarefas (To-Do List), desenvolvida como desafio técnico backend.

A aplicação permite criar, listar, atualizar, deletar e filtrar tarefas, aplicando regras de negócio e validações conforme especificado no case.

---

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- MongoDB Atlas (NoSQL)
- Maven
- Postman (testes)

---

## 🧱 Arquitetura do projeto

O projeto foi organizado em camadas:

- Controller → exposição dos endpoints REST
- Service → regras de negócio e validações
- Repository → acesso ao banco de dados MongoDB
- Exception Handler → tratamento global de erros

---

## 📦 Modelo de Dados

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

---

## ⚙️ Regras de negócio implementadas

- Título obrigatório
- Título deve ter entre 3 e 100 caracteres
- Prioridade obrigatória
- Data de vencimento não pode ser no passado
- Tarefas com status COMPLETED não podem ser editadas
- IDs gerados de forma sequencial utilizando collection de contadores no MongoDB
- Uso de Enums para garantir integridade dos valores de status e prioridade

---

## 🔌 Endpoints

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

---

## 🧪 Exemplo de criação de tarefa

{
"title": "Estudar Spring Boot",
"description": "Revisar controllers",
"priority": "HIGH",
"dueDate": "2026-04-01"
}

---

## ❗ Tratamento de erros

A API retorna erros estruturados:

{
"timestamp": "2026-03-11T20:00:00",
"status": 400,
"error": "Bad Request",
"message": "Título é obrigatório",
"path": "/tasks",
"method": "POST"
}

Erros tratados:

- validações de dados
- enum inválido
- recurso não encontrado
- JSON inválido
- erro interno inesperado

---

## ▶️ Como rodar o projeto

1. Clonar o repositório

git clone <url-do-repositorio>

2. Configurar MongoDB

Editar o arquivo:

src/main/resources/application.properties

Adicionar:

spring.data.mongodb.uri=<connection_string>

3. Rodar a aplicação

mvn spring-boot:run

Ou executar a classe principal no IntelliJ.

---

## 🧪 Testes realizados

A API foi testada utilizando Postman, cobrindo:

- CRUD completo
- filtros por status e prioridade
- validações obrigatórias
- bloqueio de edição de tarefas COMPLETED
- enums inválidos
- recurso inexistente
- JSON mal formatado

---

## 💡 Decisão de linguagem

O desafio indicava Golang como linguagem preferencial, porém permitia outras linguagens backend.

Foi escolhida a stack Java + Spring Boot visando garantir uma implementação sólida, com foco em organização de código, validações, regras de negócio e integração com banco NoSQL.

---

---
    

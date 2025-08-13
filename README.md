# FórumHub

## 💡 Sobre o Projeto 

O **FórumHub** é uma API REST desenvolvida com o objetivo de simular o funcionamento de um fórum de dúvidas como o da plataforma Alura. Esse fórum permite a interação entre usuários, professores e moderadores, promovendo aprendizagem colaborativa.

A proposta é implementar o back-end completo do fórum, incluindo autenticação, CRUD de tópicos e boas práticas de desenvolvimento com Spring Boot.

---

## 📌 Funcionalidades

A API permite que os usuários realizem as seguintes operações com tópicos:

- ✅ Criar um novo tópico  
- 📋 Listar todos os tópicos  
- 🔍 Visualizar um tópico específico  
- ✏️ Atualizar um tópico existente  
- 🗑️ Deletar um tópico  

---

## 🔐 Autenticação

A aplicação implementa **autenticação e autorização** utilizando o Spring Security. Apenas usuários autenticados podem acessar as rotas da API.

---

## 🛠 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate Validator
- PostgreSQL
- Maven
- Lombok

---

## 🧱 Estrutura do Projeto

- **DTOs** de entrada e retorno para desacoplamento de entidades
- **Validações** com anotações e regras de negócio
- **Service Layer** para a lógica da aplicação
- **Exceções Personalizadas** para controle e clareza de erros
- **Autenticação JWT** para segurança da aplicação

---

## 🗃️ Banco de Dados

Foi utilizada uma base de dados relacional (PostgreSQL) com estrutura baseada em um diagrama previamente definido. A persistência dos dados é feita por meio do Spring Data JPA.

---

## 📅 Organização do Projeto

Utilizamos o Trello para gerenciamento ágil das tarefas. As colunas do quadro são:

- **Pronto para começar**: tarefas não iniciadas
- **Em desenvolvimento**: tarefas em andamento
- **Pausado**: tarefas em espera
- **Concluído**: tarefas finalizadas

> Obs.: o uso do Trello serve apenas para organização pessoal, não sendo avaliado no projeto.

---

## 🚀 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repo.git

2. Navegue até o diretório do projeto:
   ```bash
   cd forumhub
3. Configure o banco de dados no application.properties ou application.yml
4. Rode a aplicação com sua IDE


## 🧠 Autor

Desenvolvido por [Romulo Oliveira](https://github.com/OliveiraRmulo).



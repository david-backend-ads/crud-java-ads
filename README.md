# Java CRUD de Usuários

Projeto acadêmico em Java que implementa um CRUD (Create, Read, Update, Delete) para gerenciamento de usuários via terminal.
O objetivo é aplicar conceitos de Programação Orientada a Objetos, organização em camadas e boas práticas de código.

---

## 📋 Funcionalidades

- Adicionar usuários com validação de nome e CPF
- Listar todos os usuários cadastrados
- Buscar usuário por ID, CPF ou nome (parcial)
- Remover usuário por ID
- Validações robustas para evitar dados inválidos

---

## 📌 Arquitetura

Este projeto segue uma organização em camadas para manter o código limpo e fácil de manter:

- **model**: entidades do sistema (ex.: `Usuario`) e seus atributos.
- **repository**: acesso e manipulação dos dados (CRUD), concentrando a lógica de persistência.
- **service**: regras de negócio e validações, fazendo a ponte entre a interface e o repositório.

Fluxo básico:
`Interface/Menu → Service → Repository → Dados`

---

## 🛠 Tecnologias

- **Java** (aplicação console)
- **Scanner** para entrada de dados via terminal
- **Git & GitHub** para versionamento

---

## 🚀 Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/KiritoN9/crud-java-ads.git
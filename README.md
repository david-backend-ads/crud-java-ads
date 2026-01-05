# Java CRUD de Usuários

Este é um projeto em Java que implementa um CRUD (Create, Read, Update, Delete) simples para gerenciamento de usuários. 
Ele utiliza Scanner para interação via terminal e valida dados de entrada como nome e CPF, garantindo integridade dos dados.

---

## Funcionalidades

- Adicionar usuário com validação de nome e CPF.
- Listar todos os usuários cadastrados.
- Buscar usuário por **ID**, **CPF** ou **nome** (parcial).
- Deletar usuário por **ID**.
- Validações robustas para:
  - Nome: apenas letras e espaços.
  - CPF: exatamente 11 números, sem duplicatas.
  - Opções do menu: números válidos de 1 a 7, evitando que o programa quebre com entradas inválidas.

---

## Tecnologias

- Java 17+ (ou versão compatível)
- Conceitos de **POO**: classes, objetos e métodos.
- Estruturas de dados: `List` para armazenamento temporário de usuários.

---

## Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/java-crud-ads.git

# Clientes API

API REST para gerenciamento de clientes, construída com **Java + Spring Boot + Spring Data JPA + H2**.

> Este guia foi escrito para ser bem prático e direto ao ponto: você terá aqui os **endpoints**, exemplos de uso e um **passo a passo completo para rodar a API** (instalação, configuração e teste em ferramentas como Postman/Insomnia).

---

## 1) Visão geral do projeto

A API permite:

- Criar clientes.
- Listar clientes com paginação.
- Buscar cliente por ID.
- Atualizar cliente.
- Remover cliente.
- Filtrar clientes por tipo (`COMUM` ou `VIP`).

### Entidade principal: `Client`

Campos relevantes:

- `id` (UUID)
- `name` (String)
- `login` (String)
- `password` (String)
- `cpf` (String validada e formatada)
- `type` (`COMUM` ou `VIP`)
- `registrationDate` (data)

---

## 2) Pré-requisitos

Antes de executar, instale:

1. **JDK 17+** (recomendado: 17 ou superior).
2. **Maven 3.9+** (ou usar o Maven instalado no sistema).
3. **Git** (opcional, para clonar).
4. Uma ferramenta de testes de API:
   - Postman, ou
   - Insomnia.

### Como verificar se está tudo instalado

No terminal:

```bash
java -version
mvn -version
git --version
```

Se algum comando falhar, instale o software correspondente e repita.

---

## 3) Como executar a API (passo a passo)

## Passo 1 — Obter o projeto

Se ainda não tem o código:

```bash
git clone <url-do-repositorio>
cd clientesAPI
```

Se já está com o projeto local, apenas entre na pasta raiz.

## Passo 2 — Conferir configurações

As principais configurações estão em `src/main/resources/application.properties`:

- Banco em memória H2.
- Console H2 habilitado em `/h2-console`.
- JPA com `ddl-auto=update`.

Configuração atual:

- URL JDBC: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: `password`
- Driver: `org.h2.Driver`

## Passo 3 — Subir a aplicação

Na raiz do projeto:

```bash
mvn spring-boot:run
```

Se subir com sucesso, a API ficará disponível (por padrão) em:

```text
http://localhost:8080
```

## Passo 4 — Validar se está online

Teste rápido:

```bash
curl -i http://localhost:8080/clients
```

Se retornar `200 OK` com JSON paginado, está funcionando.

---

## 4) Banco H2 e console web

Com a API rodando, abra:

```text
http://localhost:8080/h2-console
```

Use:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User Name**: `sa`
- **Password**: `password`

Após conectar, você pode consultar a tabela de clientes:

```sql
SELECT * FROM TB_CLIENTS;
```

---

## 5) Endpoints da API

Base URL:

```text
http://localhost:8080
```

## 5.1 Criar cliente

- **Método:** `POST`
- **Rota:** `/clients`
- **Status de sucesso:** `201 Created`

### Body JSON

```json
{
  "name": "Maria Silva",
  "login": "maria.silva",
  "password": "123456",
  "cpf": "12345678901",
  "type": "VIP",
  "registrationDate": "21/04/2026"
}
```

> Observação: `registrationDate` é opcional, pois o sistema preenche a data automaticamente no persist/update.

### cURL

```bash
curl -X POST "http://localhost:8080/clients" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Maria Silva",
    "login": "maria.silva",
    "password": "123456",
    "cpf": "12345678901",
    "type": "VIP",
    "registrationDate": "21/04/2026"
  }'
```

---

## 5.2 Listar clientes (paginado)

- **Método:** `GET`
- **Rota:** `/clients`
- **Status de sucesso:** `200 OK`

### Query params opcionais

- `page` (padrão: `0`)
- `size` (padrão: `10`)
- `sort` (padrão: `createdDate`)
- `direction` (padrão: `desc`)

### Exemplo

```bash
curl "http://localhost:8080/clients?page=0&size=10&sort=createdDate&direction=desc"
```

---

## 5.3 Buscar cliente por ID

- **Método:** `GET`
- **Rota:** `/clients/{id}`
- **Status de sucesso:** `200 OK`

### Exemplo

```bash
curl "http://localhost:8080/clients/0f55f4f4-d9cb-4d2f-b4ea-cf5b2b350944"
```

---

## 5.4 Atualizar cliente

- **Método:** `PUT`
- **Rota:** `/clients/{id}`
- **Status de sucesso:** `200 OK`

### Body JSON (exemplo)

```json
{
  "id": "0f55f4f4-d9cb-4d2f-b4ea-cf5b2b350944",
  "name": "Maria Silva Atualizada",
  "login": "maria.silva",
  "password": "654321",
  "cpf": "12345678901",
  "type": "COMUM",
  "registrationDate": "21/04/2026"
}
```

> Importante: no código atual, o `id` enviado na URL **não é aplicado automaticamente** no objeto salvo; por isso, para atualizar corretamente, envie também o `id` no body.

### cURL

```bash
curl -X PUT "http://localhost:8080/clients/0f55f4f4-d9cb-4d2f-b4ea-cf5b2b350944" \
  -H "Content-Type: application/json" \
  -d '{
    "id": "0f55f4f4-d9cb-4d2f-b4ea-cf5b2b350944",
    "name": "Maria Silva Atualizada",
    "login": "maria.silva",
    "password": "654321",
    "cpf": "12345678901",
    "type": "COMUM",
    "registrationDate": "21/04/2026"
  }'
```

---

## 5.5 Excluir cliente

- **Método:** `DELETE`
- **Rota:** `/clients/{id}`
- **Status de sucesso:** `200 OK`

### Exemplo

```bash
curl -X DELETE "http://localhost:8080/clients/0f55f4f4-d9cb-4d2f-b4ea-cf5b2b350944"
```

---

## 5.6 Buscar clientes por tipo

- **Método:** `GET`
- **Rota:** `/clients/search`
- **Status de sucesso:** `200 OK`

### Query params

- `type` (**obrigatório**): `COMUM` ou `VIP`
- `page` (opcional, padrão: `0`)
- `size` (opcional, padrão: `10`)
- `sort` (opcional, padrão: `name`)
- `direction` (opcional, padrão: `asc`)

### Exemplo

```bash
curl "http://localhost:8080/clients/search?type=VIP&page=0&size=10&sort=name&direction=asc"
```

---

## 6) Validações e regras

Na criação/atualização de cliente:

- `name`: obrigatório.
- `login`: obrigatório.
- `password`: obrigatório.
- `cpf`: obrigatório e validado (deve ser CPF válido).
- `type`: obrigatório (`COMUM` ou `VIP`).

Se houver erro de validação, a API retorna erro HTTP (geralmente `400`).

---

## 7) Testando no Postman (passo a passo)

1. Abra o Postman.
2. Clique em **New > HTTP Request**.
3. Escolha método e URL (ex.: `POST http://localhost:8080/clients`).
4. Aba **Body > raw > JSON**.
5. Cole o JSON de exemplo.
6. Clique em **Send**.
7. Salve em uma Collection (ex.: `Clientes API`).

Dica de fluxo no Postman:

1. `POST /clients` (criar)
2. `GET /clients` (listar)
3. `GET /clients/{id}` (detalhar)
4. `PUT /clients/{id}` (atualizar)
5. `GET /clients/search?type=VIP` (filtrar)
6. `DELETE /clients/{id}` (remover)

---

## 8) Testando no Insomnia (passo a passo)

1. Abra o Insomnia.
2. Crie um projeto novo (ex.: `Clientes API`).
3. Crie requests para cada endpoint.
4. Defina `Content-Type: application/json` em `POST` e `PUT`.
5. Execute e valide os status HTTP.

---

## 9) Estrutura de resposta (resumo)

As respostas de listagem usam paginação padrão do Spring (`content`, `totalElements`, `totalPages`, etc.).

As respostas de cliente retornam os campos de `ClientResponse`:

- `id`
- `name`
- `cpf`
- `type`
- `registrationDate`

---

## 10) Solução de problemas comuns

### Porta 8080 ocupada

Erro típico: “Port already in use”.

Solução rápida: definir outra porta em `application.properties`:

```properties
server.port=8081
```

### `mvn: command not found`

Instale Maven e reinicie o terminal.

### Java incompatível

Garanta JDK 17 ou superior:

```bash
java -version
```

### Erro de CPF inválido

Use um CPF válido no payload.

---

## 11) Comandos úteis

Executar API:

```bash
mvn spring-boot:run
```

Gerar pacote:

```bash
mvn clean package
```

Rodar testes:

```bash
mvn test
```

---

## 12) Tecnologias utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Lombok

---

Se quiser, eu também posso montar uma **coleção Postman pronta para importação** (JSON) com todos os endpoints já configurados.

# 🏃‍♂️ RunAPI

API REST desenvolvida com **Spring Boot** para gerenciamento de **usuários**, **localizações** e **corridas**, com foco em segurança, desempenho e integração com serviços externos de rotas.

---

## ✨ Funcionalidades

- Cadastro e gerenciamento de usuários
- Registro de localizações
- Criação e consulta de corridas entre localizações
- Cálculo de rotas e distâncias reais via **OpenRouteService API**

---

## 🔒 Segurança

- Autenticação e autorização com **JWT Token** utilizando **Spring Security**
- Proteção de endpoints restritos para usuários autenticados

---

## ⚙️ Recursos Técnicos

- **Paginação** e **ordenamento** nos endpoints com parâmetros customizáveis
- **Content Negotiation** com suporte a respostas em **JSON** e **XML**
- **Validação de dados** com **Spring Boot Starter Validation**
- Integração com a **OpenRouteService API** para cálculo de **distância e rotas geográficas**
- Padrão **RESTful** com boas práticas no design da API

## 🛠 Tecnologias Utilizadas
- Spring WebFlux
- Spring Data JPA
- PostgreSQL
- OpenRouteService API
- Spring Security
- Spring Bean Validation
- MapStruct
- Lombok

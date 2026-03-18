# 🚀 Desafio Itaú - API de Transações e Estatísticas

Esta API foi desenvolvida como parte do desafio técnico para a posição de Desenvolvedor(a) Software na **Itaú Unibanco**. O objetivo é processar transações financeiras em tempo real e fornecer estatísticas agregadas dos últimos 60 segundos.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**: Utilizando as últimas features como *Records* e *Parallel Streams*.
- **Spring Boot 3.4.x**: Base do ecossistema da aplicação.
- **Spring Doc (OpenAPI/Swagger)**: Documentação interativa da API.
- **JUnit 5**: Garantia de qualidade através de testes unitários.
- **Docker**: Containerização para deploy simplificado e isolamento de ambiente.
- **Lombok**: Redução de código boilerplate.

---

## 📌 Funcionalidades e Endpoints

A API segue rigorosamente os contratos definidos no desafio:

- **`POST /transacao`**: Recebe o valor e a data/hora (ISO 8601).
  - Valida se o valor é positivo e se a data não é futura.
  - Retornos: `201 Created` ou `422 Unprocessable Entity`.
- **`DELETE /transacao`**: Limpa todos os dados armazenados em memória.
  - Retorno: `200 OK`.
- **`GET /estatistica`**: Retorna estatísticas (soma, média, min, max, count) dos últimos 60 segundos.
  - Retorno: `200 OK` com JSON.

---

## 🧠 Decisões de Engenharia

Para atender aos requisitos de alta performance e segurança, foram tomadas as seguintes decisões:

1. **Thread-Safety em Memória**: Como o uso de bancos de dados foi proibido, utilizei `Collections.synchronizedList` para garantir a integridade dos dados em acessos concorrentes.
2. **Alta Performance (O(n))**: O cálculo das estatísticas utiliza a **Stream API** do Java com `DoubleSummaryStatistics`, processando todos os dados em uma única passagem. Para otimização em larga escala, implementei o uso de `parallelStream()`.
3. **Configuração Dinâmica**: A janela de tempo (60s) é configurável via `application.properties`, permitindo ajustes sem alteração no código-fonte.
4. **Tratamento de Erros Silencioso**: Implementação de um `GlobalExceptionHandler` para garantir que erros internos não exponham stacktraces, retornando apenas os status HTTP `400` ou `422` conforme solicitado.
5. **Observabilidade**: Logs de performance foram adicionados para monitorar o tempo de processamento de cada requisição em milissegundos.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- Java 21
- Maven 3.9+
- Docker (opcional)

### Execução Local
```bash
# Clonar o repositório
git clone [https://github.com/ohhigordev/desafio_devjr_itau.git](https://github.com/ohhigordev/desafio_devjr_itau.git)

# Entrar na pasta
cd desafio_devjr_itau

# Compilar e rodar
mvn spring-boot:run

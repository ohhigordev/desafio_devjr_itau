# Desafio de Programação - Itaú Unibanco

API REST para recebimento de transações e cálculo de estatísticas em tempo real.

## 🚀 Tecnologias
- Java 17
- Spring Boot 4
- Maven
- Lombok

## 📌 Funcionalidades
- **POST /transacao**: Recebe transações e valida regras de negócio.
- **DELETE /transacao**: Limpa o histórico de transações em memória.
- **GET /estatistica**: Calcula estatísticas dos últimos 60 segundos.

## 🛠️ Decisões de Engenharia
- Armazenamento em memória (Thread-safe).
- Uso de `DoubleSummaryStatistics` para alta performance.

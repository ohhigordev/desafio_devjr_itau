# Etapa 1: Build (Compilação)
# Usamos uma imagem com Maven e Java 21 para gerar o JAR
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime (Execução)
# Usamos uma imagem leve apenas com o JRE para rodar a aplicação
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copia o JAR gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
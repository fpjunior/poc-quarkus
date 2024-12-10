# Etapa 1: Construção da aplicação
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

# Copie o POM e os arquivos do projeto
COPY pom.xml ./
COPY src ./src


# Baixe as dependências e compile o projeto
RUN mvn clean package -DskipTests

# Etapa 2: Execução da aplicação
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copie o JAR gerado na etapa de build
COPY --from=builder /app/target/code-com-quarks-1.0.0-SNAPSHOT-runner.jar app.jar

# Configurar variáveis de ambiente
ENV QUARKUS_DATASOURCE_URL=jdbc:oracle:thin:@db:1521:xe \
    QUARKUS_DATASOURCE_USERNAME=cdit \
    QUARKUS_DATASOURCE_PASSWORD=abcd1234

# Exponha a porta do Quarkus
EXPOSE 8080

# Comando para executar o backend
ENTRYPOINT ["java", "-jar", "app.jar"]
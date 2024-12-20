# Etapa 1: Build da aplicação
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /workspace

# Copiar os arquivos necessários
COPY pom.xml .
COPY src ./src

# Baixar dependências e compilar o projeto
RUN mvn clean package -DskipTests -Dquarkus.package.type=uber-jar

# Etapa 2: Configuração do ambiente de execução
FROM openjdk:21-jdk-slim AS runtime

WORKDIR /work

# Copiar o JAR gerado
COPY --from=builder /workspace/target/*.jar /work/app.jar

# Comando para rodar o aplicativo
CMD ["java", "-jar", "/work/app.jar"]

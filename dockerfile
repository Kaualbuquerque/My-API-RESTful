# Etapa 1: Build da aplicação (usando Maven)
FROM maven:3.9.1-eclipse-temurin-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn clean install -DskipTests

# Copia o código fonte para o container
COPY src /app/src

# Etapa 2: Executando a aplicação (usando uma imagem base para Java)
FROM eclipse-temurin:17-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR gerado pela etapa de build
COPY --from=build /app/target/my-api-0.0.1-SNAPSHOT.jar /app/my-api.jar

# Expõe a porta 8080 para a aplicação
EXPOSE 8080

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "/app/my-api.jar"]

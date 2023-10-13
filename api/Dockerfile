# Imagem base
FROM maven:3.8.4-openjdk-17 AS build

# Diretório no contêiner
WORKDIR /app

# Copia os arquivos pom.xml e src para o diretório de trabalho atual
COPY pom.xml ./
COPY src ./src/

# Faz o build do projeto
RUN mvn clean package -DskipTests

# Início de uma nova etapa de build para a imagem final
FROM openjdk:17-jdk-alpine

# Diretório no contêiner
WORKDIR /app

# Copia o jar do estágio de build
COPY --from=build /app/target/*.jar app.jar

# Porta que a aplicação usará
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java","-jar","/app/app.jar"]

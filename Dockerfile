
# Usar uma imagem base do OpenJDK para rodar a aplicação
FROM openjdk:11-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado para dentro do container
COPY target/Novo-BD-EBAC-Teste-1.0-SNAPSHOT.jar /app/Novo-BD-EBAC-Teste-1.0-SNAPSHOT.jar

# Expor a porta que o aplicativo vai utilizar, se necessário
EXPOSE 8080

# Comando para executar o arquivo JAR
CMD ["java", "-jar", "Novo-BD-EBAC-Teste-1.0-SNAPSHOT.jar"]


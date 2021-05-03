# Pauta Service [![selo](https://github.com/DiovaneMendes/pauta-service/.github/workflows/gradle/badge.svg)](https://github.com/DiovaneMendes/pauta-service/actions)

Serviço dedicado a criação e votação de pautas.

## Tecnologias
- Java 11
- SpringBoot
- Docker
- MyBatis
- PostgreSQL
- Pitest
- Sonarqube

## Documentação: 
#### Docker
- Há um arquivo compose com a configuração para subir o PostgreSQL e o PgAdmin(interface gráfica).

***Obs.:*** Possivelmente terá que ajustar o caminho do "volumes" do PostgreSQL.

#### Banco
- Script com a estrutura está em: /src/main/resources/;
- Há um "diagrama relacional" na issue: https://github.com/DiovaneMendes/pauta-service/issues/1

#### Swagger
http://localhost:8080/swagger-ui.html

## Sonarqube
#### Execução:
./gradlew sonarqube \
  -Dsonar.projectKey=pauta-service \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=094d9a9167aafaae57c4cda4a985316f73202b72


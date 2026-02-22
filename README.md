# Autoflex BackEnd
Este projeto foi utilizado para um teste prático para a Autoflex. A arquitetura utilizada é `Springboot` com Maven

## Pré-requisitos

- JDK 17
- Docker Desktop.

Se você clicar no card abaixo, ele leva para o site da documentação/download do requisito listado acima.

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Docker](https://img.shields.io/badge/Docker-1D63ED?style=for-the-badge&logo=docker&logoColor=white)](https://docs.docker.com/desktop/setup/install/windows-install/)

## Requisitos para iniciar a API:

É necessário instalar o Docker Desktop. Após ter o mesmo instalado, você conseguirá rodar o Back-End normalmente.

- OBS: Após o clone do projeto e com o Docker instalado, você deverá abrir um terminal na pasta na qual está o projeto, dentro dessa pasta vai ter um "`docker-compose.yml`". Neste terminal você deve iniciar o seguinte comando:
  ```console
  docker-compose up
  ```
Este comando serve para subir o banco de dados para o docker. Após essa etapa, basta somente iniciar o projeto via IntelliJ ou a IDE de sua preferência.

> [!TIP]
> Pode ser que sua IDE não reconheça de primeira que o projeto é Maven, recomendo que busque como "forçar" a atualização do projeto para Maven na sua IDE.

## Documentação:

Segue o link para acessar a [Documentação](https://github.com/guilherme-miranda04/autoflex-back-end/blob/main/src/README.md), contendo todos os endpoints com métodos, body necessário e os callbacks dos mesmos.

## Rodando

O projeto utiliza um [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) embutido para o build, bastando apenas ter o [OpenJDK](https://openjdk.org/) 25+ instalado em sua máquina.

### Banco de Dados

Para funcionar corretamente, o sistema usa um container com [PostgreSQL](https://www.postgresql.org/). Para fazer sua devida inicialização, é necessário ter o [Docker](https://www.docker.com/) e o [Docker Compose](https://docs.docker.com/compose/) instalados em sua máquina.

Após a instalação, será necessário configurar duas variáveis de ambiente do PostgreSQL para realizar a autenticação com o banco. Para isso, basta apenas criar uma cópia do arquivo `.env.example` do repositório chamada `.env` e colocar suas credenciais como argumentos.

| Variável          | Descrição                            |
| ----------------- | ------------------------------------ |
| POSTGRES_USER     | O nome do seu usuário no banco.      |
| POSTGRES_PASSWORD | A senha que será utilizada no banco. |

E por fim, realizar a composição do banco.

```sh
docker compose up -d
```

### Execução

Rode o comando relativo ao seu sistema operacional na raiz do projeto.

#### Linux, Mac e BSD

```sh
./gradlew run
```

#### Windows

```bat
.\gradlew.bat run
```

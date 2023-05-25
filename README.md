# Webapp

## Requisitos obrigatórios:
* jdk-11
* Apache Maven - *v3+*

*Obs: A aplicação por padrão ficará no endereço [http://localhost:8080/web-app](http://localhost:8080/web-app)*

## Para a utilização da aplicação sem o uso do Docker, será necessário:
* 1 - Configuração dos arquivos que se referem a bancos de dados, apontando para um banco existente
* 2 - Executar script para criação de Tabela e linhas iniciais no banco de dados
* 3 - Configurar servidor de aplicação JBoss (O projeto foi desenvolvido utilizando a versão *Wildfly-16.0.0.Final*)
___

## 1 - Configuração dos arquivos

Nos arquivos [liquibase.properties](src/main/resources/liquibase.properties) e [ConnectionFactory.java](src/main/java/br/com/webapp/infra/ConnectionFactory.java) altere as propriedades para as que serão utilizadas no banco de dados;

---

## 2 - Execução do Script complementar para o Banco de dados
Certifique-se de criar a Database 'webapp' no banco de dados que os arquivos referentes à configuração estão apontados.\
Após isto, execute o comando
``` mvn liquibase:update ```

---

## 3 - Crie um servidor de aplicação JBoss
* Baixe a versão desejada do wildfly/jboss através de [WildFly - Downloads](https://www.wildfly.org/downloads/)
* O WildFly pode ser configurado de acordo com as suas preferências seguindo a documentação [WildFly Documentation](https://docs.wildfly.org/16/)
* Configure sua IDE para fazer Deploy do seu arquivo .war sempre que o servidor JBoss for inicializado

# Docker 
Para o funcionamento da aplicação com Docker, será necessário seguir 3 etapas:
* 1 - Configuração de rede do Docker
* 2 - Criação do container do MySQL
* 3 - Criação do container do JBoss + deploy da aplicação

---
## 1 - Configuração de rede

### Cria uma rede para que os containers do MySQL e do JBoss possam se comunicar

``` docker network create mynet ```

---
## 2 - Criação do container do MySQL

### Cria uma imagem do MySQL e a executa em um container

*Obs: A senha pode ser alterada para a de sua preferência, neste caso está como 'Admin123!'*

``` docker run --net mynet --name mysql -e MYSQL_ROOT_PASSWORD=Admin123! -d mysql ```

### Acessa o container que contém o MySQL e permite realizar alterações no banco de dados

*Obs: Será necessário acessá-lo de modo interativo (-it) com a linguagem bash*

``` docker exec -it mysql bash ```

### Acessa o MySQL dentro do container para realizar as modificações necessárias
``` mysql -u root -p ```

### Cria o banco de dados *webapp*, que será necessário para que a aplicação funcione
``` CREATE DATABASE webapp; ```

---
## 3 - Criação do container do JBoss + deploy da aplicação

### Alteração dos arquivos de configuração do banco de dados
É necessário que você altere as urls dos bancos de dados acessados no sistema e os que serão alterados pelo liquibase.
Pode fazê-los da seguinte forma:

#### 1 - Para saber o endereço do seu banco de dados no container, utilize: 

``` docker inspect mysql ```

O endereço vai ser descrito na propriedade 'IPAddress' 

#### 2 - No arquivo [liquibase.properties](src/main/resources/liquibase.properties) altere o arquivo com as propriedades do seu banco de dados;

#### 3 - No arquivo [ConnectionFactory.java](src/main/java/br/com/webapp/infra/ConnectionFactory.java) altere o arquivo com as propriedades do seu banco de dados;

### Gera as alterações no banco de dados com o liquibase

``` mvn liquibase:update ```

### Gera o arquivo '.war' do sistema

dentro do diretório do projeto, utilize o comando

``` mvn clean package -DskipTests ```

### Cria uma imagem de acordo com as configurações escritas no Dockerfile
``` docker build -t webapp . ```

### Cria um container a partir da imagem gerada pelo Dockerfile
- Executa de modo interativo *(-it)*,
- mapeando as portas 8080 e 9990 do container com as da máquina *(-p 8080:8080 -p 9990:9990)*,
- faz com que as configurações do servidor possam ser acessadas a partir do ip local da máquina *(/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0)*

``` docker run --net mynet -it --rm -p 8080:8080 -p 9990:9990 webapp /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 ```

---

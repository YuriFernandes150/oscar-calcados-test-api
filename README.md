# oscar-calcados-api
### API criada como um teste de CRUD de calçados.

## Configuração básica.

Essa API está no formato ```.JAR```, o que significa que você vai precisar rodá-la usando o console da JVM

```
java -jar oscar-calcados-api.jar
```

Mas antes disso, será necessário configurar seu sistema.

## Requisitos

Essa API requer o [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)  e o [Java 8](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html) ou superior.

Após o download e configuração do seu BD, você precisará criar um arquivo ```.properties``` para configurar a API. Segue um exemplo do arquivo:

```properties
spring.datasource.url=jdbc:postgresql://YourDataBaseHost:YourDataBasePort/YourDataBaseName
spring.datasource.username=YourDataBaseUsername
spring.datasource.password=YourDataBasePassword

spring.datasource.driverClassName=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

```
Após essa configuração, você poderá utilizar a API.

## Uso
Essa API funciona através de diversas URLs de endpoints, você precisará enviar requisições para a API para utilizar as funçãoes CRUD.
A API funciona na porta ```8080```. Nesse caso utilizaremos como exemplo a API sendo hosteada na máquina local, assim , endpoint ficaria:
```
http://localhost:8080/
```

A partir disso, vamos construir outras requisições.

###Endpoints
A API realiza operações com calçados, portanto temos no banco propriedades como  descrição, tamanho, cor, etc.

Contamos então com os seguintes Endpoints base:
##### Calçado
```
http://localhost:8080/calcado
```

##### Tamanho
```
http://localhost:8080/tamanho-calcado
```

##### Cor
```
http://localhost:8080/cor-calcado
```

##### Marca
```
http://localhost:8080/marca-calcado
```

##### Categoria
```
http://localhost:8080/categoria-calcado
```

Com exceção do endpoint de Calçado, todos os outros não requerem requests muito complexas.

### POST e PUT - Cadastrando e Atualizando dados

#### Post

Para começar a cadastrar os dados, é necessário enviar Requests POST em formato JSON para o Endpoint base.

#### PUT

Para atualizar dados, é necessário incluir o id do Objeto ao final do Endpoint.

##### Ex:
Atualizando o registro de ID 1

```
http://localhost:8080/tamanho-calcado/1
```

Em ambos os casos, o mesmo formato de JSON é enviado, abaixo seguem exemplos de JSON. (Lembre-se que esses JSONs podem ser utilizados tanto para POST, quanto para PUT,mudando somente a URL do Endpoint).

#### Tamanho
É necessário enviar um JSON válido para o EndPoint base de tamanho.
``` json
{
    "descricaoTamanhoCalcado":"42"
}

```
Em caso de sucesso, a API retorna:

```json
{
    "idTamanhoCalcado": 1,
    "descricaoTamanhoCalcado": "42",
    "dataCadastroTamanho": "2022-09-26T18:36:50.492"
}
```

#### Marca
É necessário enviar um JSON válido para o EndPoint base de Marca.
```json
{
     "descricaoMarcaCalcado":"MOLEKA"
}
```
Em caso de sucesso, a API retorna:
```json
{
    "idMarcaCalcado": 44,
    "descricaoMarcaCalcado": "MOLEKA",
    "dataCadastroMarca": "2022-09-26T21:08:05.603"
}

```
#### Categoria
É necessário enviar um JSON válido para o EndPoint base de Categoria.
```json
{
    "descricaoCategoria":"ESPORTIVO"
}
```
Em caso de sucesso, o retorno será:
```json
{
     "idCategoriaCalcado": 32,
     "descricaoCategoria": "ESPORTIVO",
     "dataCadastroCategoria": "2022-09-26T16:40:47.319"
}
```

#### Cor
É necessário enviar um JSON válido para o EndPoint base de Cor.
```json
{
     "descricaoCorCalcado":"BEGE"
}

```
Em caso de sucesso, a API retorna o seguinte JSON:
```json
{
     "idCorCalcado": 34,
     "descricaoCorCalcado": "BEGE",
     "dataCadastroCor": "2022-09-26T17:06:20.105"
}
```

Todas essas caracteristicas serão usadas pelo Objeto Calçado, então se certifique de que cadastrou pelo menos uma de cada antes de cadastrar um calçado.

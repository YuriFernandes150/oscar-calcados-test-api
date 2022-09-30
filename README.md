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
Essa API funciona através de diversas URLs de endpoints, você precisará enviar requisições para a API para utilizar as funções CRUD.
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

Em ambos os casos, o mesmo formato de JSON é enviado, abaixo seguem exemplos de JSON. (Lembre-se que esses JSONs podem ser utilizados tanto para POST, quanto para PUT, mudando somente a URL do Endpoint).

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

#### Cadastrando/Atualizando um calçado
Para cadastrar ou atualizar um calçado, você precisa especificar também os outros objetos ```Marca, Categoria, Cor e Tamanho```. Esses objetos precisam ser especificados com todos os seus campos.

```json

{
    "codCalcado":"01",
    "descricaoCalcado":"CHINELO DE DEDO",
    "qtdEstoque":200.0,
    "precoCalcado":18.5,
    "corCalcado":{
        "idCorCalcado":8,
        "descricaoCorCalcado":"PRETO",
        "dataCadastroCor":"2022-09-28T22:17:48.029"
    },
    "marcaCalcado":{
        "idMarcaCalcado":7,
        "descricaoMarcaCalcado":"HAVAIANA",
        "dataCadastroMarca":"2022-09-29T00:57:35.203"
    },
    "categoriaCalcado":{
        "idCategoriaCalcado":7,
        "descricaoCategoria":"CHINELO",
        "dataCadastroCategoria":"2022-09-28T22:00:15.862"
    },
    "tamanhoCalcado":{
        "idTamanhoCalcado":9,
        "descricaoTamanhoCalcado":"44",
        "dataCadastroTamanho":"2022-09-28T22:06:40.835"
    }
}

```

Em caso de sucesso, temos o retorno:
```json
{
    "idCalcado": 22,
    "codCalcado": "90",
    "descricaoCalcado": "CHINELO DE DEDO",
    "qtdEstoque": 200.0,
    "dataCadastro": "2022-09-29T16:55:27.6978",
    "precoCalcado": 18.5,
    "corCalcado": {
        "idCorCalcado": 8,
        "descricaoCorCalcado": "PRETO",
        "dataCadastroCor": "2022-09-28T22:17:48.029"
    },
    "marcaCalcado": {
        "idMarcaCalcado": 7,
        "descricaoMarcaCalcado": "HAVAIANA",
        "dataCadastroMarca": "2022-09-29T00:57:35.203"
    },
    "categoriaCalcado": {
        "idCategoriaCalcado": 7,
        "descricaoCategoria": "CHINELO",
        "dataCadastroCategoria": "2022-09-28T22:00:15.862"
    },
    "tamanhoCalcado": {
        "idTamanhoCalcado": 9,
        "descricaoTamanhoCalcado": "44",
        "dataCadastroTamanho": "2022-09-28T22:06:40.835"
    }
}
```


### DELETE 
O request de delete não necessita do envio de nenhum JSON, somente a requisição DELETE em si, com o id do Onjeto a ser deletado na URL:

##### Calçado
```
http://localhost:8080/calcado/{id}
```

##### Tamanho
```
http://localhost:8080/tamanho-calcado/{id}
```

##### Cor
```
http://localhost:8080/cor-calcado/{id}
```

##### Marca
```
http://localhost:8080/marca-calcado/{id}
```

##### Categoria
```
http://localhost:8080/categoria-calcado/{id}
```
Em caso de sucesso, a API envia um código HTML de OK.

### GET 
O método GET utiliza os Endpoint base como uma busca completa dos dados, o Objeto Calçado permite uma busca mais completa, com filtros e ordenação.

Para os outros objetos (```Marca, Categoria, Cor e Tamanho```), há Endpoints padrão que terminam em:

Para buscas com ID:
```/id/{id}```

Para buscas com Descrição:
```/descricao/{descricao}```

Usando como exemplo o objeto tamanho, podemos buscar assim:
```http://localhost:8080/tamanho-calcado/descricao/42```

O retorno será:
```json
[
    {
        "idTamanhoCalcado": 8,
        "descricaoTamanhoCalcado": "42",
        "dataCadastroTamanho": "2022-09-28T08:42:51.85467"
    }
]
```

É possível buscar todos os registros de uma vez, enviando um GET para o endpoint base, assim, a API retorna um objeto paginável:
```
http://localhost:8080/tamanho-calcado
```
Retorna
```json
    {
    "content": [
        {
            "idTamanhoCalcado": 7,
            "descricaoTamanhoCalcado": "34",
            "dataCadastroTamanho": "2022-09-27T18:12:36.144876"
        },
        {
            "idTamanhoCalcado": 11,
            "descricaoTamanhoCalcado": "36",
            "dataCadastroTamanho": "2022-09-29T08:42:35.455"
        }, [...]
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 5,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 5,
    "empty": false
}
```
Esse tipo de busca é aplicavel para todos os outros objetos menores.

#### Buscando Calçados
Calçados têm os endpoints de busca já mencionados acima, que retornam objetos pagináveis ou objetos simples, porém, o Objeto calçado possui um endPoint de busca personalizável.
```
http://localhost:8080/calcado/buscar
```
Esse Endpoint ceita os seguintes parâmetros:

1. descricaoCalcado - Descrição do Calçado (não precisa ser exata)
2. tamanhoCalcado - Descricao do Tamanho do Calçado (precisa ser exata)
3. marcaCalcado - Descricao da Marca do Calçado (precisa ser exata)
4. categoriaCalcado - Descricao da Categoria do Calçado (precisa ser exata)
5. corCalcado - Descrição da Cor do Calçado (precisa ser exata)
6. precoCalcado - Preço exato do calçado. (use apenas um parâmetro de preço por vez)
7. precoCalcadoGT - Procura calçados com preços maiores que o valor especificado.
8. precoCalcadoLT - Procura calçados com preços menores que o valor especificado.
9. dataCadastroGT - Procura todo os Calçados cadastrados depois da data especificada. (combine ambos para buscar entre datas)
10. dataCadastroLT - Procura todo os Calçados cadastrados antes da data especificada.

Ele também aceita parâmetros de ordenação e paginação
1. page - número da página (começa no 0).
2. sort - Ordena por campo (Ex: sort=descricaoCalcado,ASC).
3. size - Quantidade de itens por página.

### Ex: 
```
http://localhost:8080/calcado/buscar?descricaoCalcado=CHINELO&&corCalcado=PRETO
```
Nos retorna:

```json
{
    "content": [
        {
            "idCalcado": 5,
            "codCalcado": "02",
            "descricaoCalcado": "CHINELO DE DEDO",
            "qtdEstoque": 100.0,
            "dataCadastro": "2022-09-28T23:22:46.602",
            "precoCalcado": 18.5,
            "corCalcado": {
                "idCorCalcado": 7,
                "descricaoCorCalcado": "AZUL",
                "dataCadastroCor": "2022-09-28T22:01:33.823"
            },
            "marcaCalcado": {
                "idMarcaCalcado": 7,
                "descricaoMarcaCalcado": "HAVAIANA",
                "dataCadastroMarca": "2022-09-29T00:57:35.203"
            },
            "categoriaCalcado": {
                "idCategoriaCalcado": 7,
                "descricaoCategoria": "CHINELO",
                "dataCadastroCategoria": "2022-09-28T22:00:15.862"
            },
            "tamanhoCalcado": {
                "idTamanhoCalcado": 8,
                "descricaoTamanhoCalcado": "42",
                "dataCadastroTamanho": "2022-09-28T08:42:51.85467"
            }
        },
        {
            "idCalcado": 6,
            "codCalcado": "03",
            "descricaoCalcado": "CHINELO DE DEDO",
            "qtdEstoque": 50.0,
            "dataCadastro": "2022-09-28T23:24:10.861",
            "precoCalcado": 19.5,
            "corCalcado": {
                "idCorCalcado": 7,
                "descricaoCorCalcado": "AZUL",
                "dataCadastroCor": "2022-09-28T22:01:33.823"
            },
            "marcaCalcado": {
                "idMarcaCalcado": 7,
                "descricaoMarcaCalcado": "HAVAIANA",
                "dataCadastroMarca": "2022-09-29T00:57:35.203"
            },
            "categoriaCalcado": {
                "idCategoriaCalcado": 7,
                "descricaoCategoria": "CHINELO",
                "dataCadastroCategoria": "2022-09-28T22:00:15.862"
            },
            "tamanhoCalcado": {
                "idTamanhoCalcado": 7,
                "descricaoTamanhoCalcado": "34",
                "dataCadastroTamanho": "2022-09-27T18:12:36.144876"
            }
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```
Há também uma interface que interage com essa API, você pode baixá-la [aqui.](https://github.com/YuriFernandes150/oscar-calcados-interface/releases)

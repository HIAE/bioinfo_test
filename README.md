# bioinfo_test
Bem-vindo ao Teste de Bioinformática


Este teste teve por objetivo criar uma API que irá retorne o(s) fenótipo(s) de um determinado gene ou lista de genes

## Getting Started

Essas instruções fornecerão uma cópia do projeto em funcionamento em sua máquina local para fins de desenvolvimento e teste.

### Pré-requisitos

Para instalar e executar o software você precisa:

```
Python 3
PostgreSQL
```

### Instalando

Uma série passo a passo de exemplos que lhe dizem o que fazer para executar a API

O primeiro passo será criar um ambiente isolado para Python 3 no projeto (consulte o seguinte link para mais informações ```http://python-guide-pt-br.readthedocs.io/en/latest/dev/virtualenvs/``` ).Isto é importante para criar uma pasta que contenha todos os executáveis necessários para usar os pacotes que o projeto precisa. Para instalar os 'requirements' do projeto use o seguinte comando
```
pip install -r requirements.txt
```

O segundo passo consiste em executar o crawler para popular a base de dados da aplicação. 
Para isso, primeiro abra o arquivo crawler.py e configure as informações do seu banco PostgreSQL, conforme mostrado a seguir:
```
user = 'postgres'
password = 'root'
host = 'localhost'
port = '5432'
```

Depois execute o arquivo com o seguinte comando 

```
python ./crawler.py 
```

O próximo passo será a configuração da API. Abra o aquivo settings.py dentro da pasta bioinfo_api/bioinfo_api e altere as informações do banco de dados (OBS: o NAME deve ser 'bioinfo_rodrigo') como do exemplo a seguir

```
DATABASES = {
'default': {
...
'NAME': 'bioinfo_rodrigo',
'USER': 'postgres',
'PASSWORD': 'root',
'HOST': 'localhost',
'PORT': '5432',
}
}
```

Finalmente, entre no diretório bioinfo_api/ e inicie o servidor com o comando a seguir

```
python manage.py runserver
```
Se tudo deu certo até aqui, você estará pronto para testar a aplicação! aparecerá no terminal o nome do servidor onde poderá acessar por um browser web como mostrado no exemplo a seguir

```
http://127.0.0.1:8000/phenotypes/?gene=['ATR','GLI3']
http://127.0.0.1:8000/genes/
http://127.0.0.1:8000/genes/1
```

sugestão: para testes poderá também utilizar o seguinte plugin    ```https://www.getpostman.com/```


## Executando os testes

Abaixo serão descritos os métodos disponíveis.

**Phenotypes**
----
Retorna o(s) fenótipo(s) de um determinado gene ou lista de genes.

* **URL**
/phenotypes/?gene=['ATR','GLI3']

* **Method:**

`GET`

*  **URL Params**

**Required:**

`gene=[list]`

* **Data Params**

None

* **Success Response:**

* **Code:** 200 <br />
**Content:** `[
{
"hpo": "HP:0001459",
"phenotype": "1-3 toe syndactyly",
"gene": "GLI3",
"gene_id": "2737"
},
{
"hpo": "HP:0010713",
"phenotype": "1-5 toe syndactyly",
"gene": "GLI3",
"gene_id": "2737"
},
{
"hpo": "HP:0000878",
"phenotype": "11 pairs of ribs",
"gene": "ATR",
"gene_id": "545"
},
{
"hpo": "HP:0006097",
"phenotype": "3-4 finger syndactyly",
"gene": "GLI3",
"gene_id": "2737"
},
{
"hpo": "HP:0010866",
"phenotype": "Abdominal wall defect",
"gene": "GLI3",
"gene_id": "2737"
}
]`

* **Error Response:**

* **Code:** 404 NOT FOUND <br />
**Content:** `{ error : "User doesn't exist" }`

OR

* **Code:** 401 UNAUTHORIZED <br />
**Content:** `{ error : "You are unauthorized to make this request." }`


**Genes**
----
Exibe uma lista de genes e permite a criação de um novo gene.

* **URL**
/genes/

* **Method:**

`GET` | `POST`

*  **URL Params**

**Required:**

None

* **Data Params**

`hpo=[alphanumeric]`
`phenotype=[alphanumeric]`
`gene=[alphanumeric]`
`gene_id=[alphanumeric]`

* **Success Response:**


* **Code:** 200 <br />
**Content:** `{
"count": 1123107,
"next": "http://127.0.0.1:8000/genes/?limit=5&offset=15",
"previous": "http://127.0.0.1:8000/genes/?limit=5&offset=5",
"results": [
{
"hpo": "HP:0010708",
"phenotype": "1-5 finger syndactyly",
"gene": "LMBR1",
"gene_id": "64327"
},
{
"hpo": "HP:0010713",
"phenotype": "1-5 toe syndactyly",
"gene": "GLI3",
"gene_id": "2737"
},
{
"hpo": "HP:0000878",
"phenotype": "11 pairs of ribs",
"gene": "ATR",
"gene_id": "545"
},
{
"hpo": "HP:0000878",
"phenotype": "11 pairs of ribs",
"gene": "SKI",
"gene_id": "6497"
},
{
"hpo": "HP:0000878",
"phenotype": "11 pairs of ribs",
"gene": "SOX2",
"gene_id": "6657"
}
]
}`

* **Error Response:**

* **Code:** 404 NOT FOUND <br />
**Content:** `{ error : "User doesn't exist" }`

OR

* **Code:** 401 UNAUTHORIZED <br />
**Content:** `{ error : "You are unauthorized to make this request." }`


**Gene Detail View**
----
Permite visualizar, editar e deletar um gene

* **URL**
/genes/:id

* **Method:**

`GET` | `PUT` | `DELETE`

*  **URL Params**

**Required:**

id=[integer]

* **Data Params**

`hpo=[alphanumeric]`
`phenotype=[alphanumeric]`
`gene=[alphanumeric]`
`gene_id=[alphanumeric]`

* **Success Response:**


* **Code:** 200 <br />
**Content:** `{
"hpo": "HP:0001459",
"phenotype": "1-3 toe syndactyly",
"gene": "GLI3",
"gene_id": "2737"
}`

* **Error Response:**

* **Code:** 404 NOT FOUND <br />
**Content:** `{ error : "User doesn't exist" }`

OR

* **Code:** 401 UNAUTHORIZED <br />
**Content:** `{ error : "You are unauthorized to make this request." }`



## Autor

* **Rodrigo de Souza Reis** - (https://www.linkedin.com/in/rodrigosourei/)

## Construido com

* [Django](https://www.djangoproject.com/) - The Web framework for perfectionists with deadlines
* [Django REST framework](http://www.django-rest-framework.org/) - A powerful and flexible toolkit for building Web APIs

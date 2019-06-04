# Instruções teste bioinfo
## Pré-requisitos:

Para rodar o projeto é necessário:
  >Python 3
  >PostgreSQL
  
Além dos pacotes e bibliotecas:
  >psycopg2
  >flask
  >flask_restful
  >requests
  >ast
  
Caso estes já não estejam instalados, instalar através do comando pip, exemplo:

  >pip install psycopg2
  
## Rodando o projeto

### Create_database
1) Create_database cria a base de dados 'rayssa_bioinfo' no banco de dados do Postgres para que essa base em comum seja acessada pelo crawler e pelo api_rayssa

2) Caso as configurações do Postgresql sejam diferentes das default, é necessário mudá-las no arquivo onde há o comentário #CHANGE NECESSARY POSTGRES SETTINGS

3) Para executar: python ./create_database.py

### Crawler
1) Crawler faz o download da informação contida no link fornecido e armazena em uma tabela na base de dados criada anteriormente

2) Caso as configurações do Postgresql sejam diferentes das default, é necessário mudá-las no arquivo onde há o comentário #CHANGE NECESSARY POSTGRES SETTINGS

3) Para executar: python ./crawler.py

### Api_rayssa
1) Api_rayssa retorna os fenótipos referentes à lista de genes fornecida.

2) Caso as configurações do Postgresql sejam diferentes das default, é necessário mudá-las no arquivo onde há o comentário #CHANGE NECESSARY POSTGRES SETTINGS

3) Para executar: python ./crawler.py
  Aparecerá resposta:
   > * Serving Flask app "api_rayssa" (lazy loading)
   > * Environment: production
   >  WARNING: Do not use the development server in a production environment.
   >  Use a production WSGI server instead.
   > * Debug mode: on
   > * Restarting with stat
   > * Debugger is active!
   > * Debugger PIN: 280-068-427
   > * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)
    
 4) Abrir o browser e fornecer a lista de genes necessária, exemplo:
 
    http://localhost:5000/phenotypetogene/genes/['CENPJ']
    
    http://localhost:5000/phenotypetogene/genes/['CENPJ','SOST']



  

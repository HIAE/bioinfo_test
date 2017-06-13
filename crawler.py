from psycopg2 import DatabaseError
from psycopg2 import connect
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT
import urllib.request

# Configuracao Banco de Dados
# CONFIGURE AQUI SEU BANCO DE DADOS
user = 'postgres'
password = 'root'
host = 'localhost'
port = '5432'



# Cria Database bioinfo
try:
	print('conectando com banco de dados ...')
	conn = connect('user=%s host=%s password=%s port=%s' % (user, host, password, port))
	conn.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)

	# Abre um cursor para realizar operacoes
	cur = conn.cursor()

	# Cria nova base de dados
	print('criando database bioinfo ...')
	cur.execute("CREATE DATABASE bioinfo_rodrigo;")
	cur.close()
	conn.close()

except (Exception, DatabaseError) as error:
	print(error)
finally:
	if conn is not None:
		conn.close()

# Cria Table genes
try:
	print('conectando com banco de dados bioinfo ...')
	conn = connect('dbname=bioinfo_rodrigo user=%s host=%s password=%s port=%s' % (user, host, password, port))

	cur = conn.cursor()

	# Cria nova tabela
	print('criando table genes ...')
	cur.execute("CREATE TABLE api_genes (id serial PRIMARY KEY, hpo varchar, phenotype varchar, gene varchar, gene_id varchar);")
	conn.commit()
	
	cur.close()
	conn.close()

except (Exception, DatabaseError) as error:
	print(error)
finally:
	if conn is not None:
		conn.close()


# Insere genes e fenotipo na base de dados bioinfo	
try:
	print('conectando com banco de dados bioinfo ...')
	conn = connect('dbname=bioinfo_rodrigo user=%s host=%s password=%s port=%s' % (user, host, password, port))

	cur = conn.cursor()
	# Abre arquivo via url
	print('lendo base de genes e fenotipos ...')
	local_filename, headers = urllib.request.urlretrieve('http://compbio.charite.de/jenkins/job/hpo.annotations.monthly/lastStableBuild/artifact/annotation/ALL_SOURCES_ALL_FREQUENCIES_phenotype_to_genes.txt')
	data = open(local_filename)
	data.readline() 

	print('salvando genes e fenotipos na base de dados bioinfo ...')
	print('-- isto pode demorar um pouco')
	for line in data:
	    values = line.split("\t")
	    cur.execute('INSERT INTO api_genes (hpo, phenotype, gene_id, gene) VALUES (%s, %s, %s, %s)', (values[0].rstrip(), values[1].rstrip(), values[2].rstrip(), values[3].rstrip()))
	    conn.commit()

	cur.close()
	conn.close()

	print('pronto')

except (Exception, DatabaseError) as error:
	print(error)
finally:
	if conn is not None:
		conn.close()
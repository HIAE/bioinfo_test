import requests
import psycopg2
import csv


url="http://compbio.charite.de/jenkins/job/hpo.annotations.monthly/lastStableBuild/artifact/annotation/ALL_SOURCES_ALL_FREQUENCIES_phenotype_to_genes.txt"

#try to download data from given url, if error check internet connection
print "downloading data"
try:
    raw_data=requests.get(url)
    raw_data_text= raw_data.text
    raw_data_csv = str(raw_data_text)
except Exception:
    print "Couldn't connet to the source url, please check your internet connection"

#try to connect to postgres database, if error check given postgres settings
#CHANGE NECESSARY POSTGRES SETTINGS EXCEPT DATABASE
print "connecting to database"
try:
    conn = psycopg2.connect("host=localhost user=postgres password=postgres port=5432 dbname=rayssa_bioinfo")
except Exception:
    print "Couldnt conenct to database: please check given postgres settings"
#creates a cursor to database
conn = psycopg2.connect("host=localhost user=postgres password=postgres port=5432 dbname=rayssa_bioinfo")
cur = conn.cursor()

#check if already exists the table api_genes_table on the database from previous executions and delete the table's content if it exists(useful if the downloaded data changes between executions)

cur.execute("SELECT exists(SELECT * FROM information_schema.tables WHERE table_name=%s)", ('api_genes',))
exists_table=cur.fetchall()

if exists_table:
    cur.execute("DELETE FROM api_genes;")

else:
    # create table to store downloaded data
    cur.execute("CREATE TABLE api_genes (hpo varchar, phenotype varchar, gene_id varchar , gene_name varchar);")

#split the downloaed data in string type into lines

lines=raw_data_csv.splitlines()

#insert the splited lines into genesKphenotypes_table skipping the header line

print "inserting downloaded data into table"

n=0

for line in lines:
    if n==0:
        n+=1
    else:
        array=[s for s in line.split('\t')]
        postgres_insert_query=""" INSERT INTO api_genes VALUES (%s,%s,%s,%s)"""
        cur.execute(postgres_insert_query, array)

print "END"

cur.close()
conn.close()
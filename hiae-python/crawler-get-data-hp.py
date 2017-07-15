import urllib
import csv
import psycopg2
from tablesDAO import TableDAO
from geneDAO import GeneDAO
from phenotypeDAO import PhenotypDAO
from phOnGeDAO import PhenotypOnGeneDAO


#Format: HPO-ID<tab>HPO-Name<tab>Gene-ID<tab>Gene-Name
HPO_ID = 0
HPO_NAME = 1
GENE_ID = 2
GENE_NAME = 3
print("START")

print("Please wait, we are downloading phenotypes file ...")
hp_file_url = 'http://compbio.charite.de/jenkins/job/hpo.annotations.monthly/lastStableBuild/artifact/annotation/ALL_SOURCES_ALL_FREQUENCIES_phenotype_to_genes.txt'
hp_file_name = "files/phenotype_to_genes.tsv"
hp_file = urllib.URLopener();
hp_file.retrieve(hp_file_url, hp_file_name)
print("Downloading finished")

try:
    dbConn = psycopg2.connect("dbname='hiae-bioinfo' user='gianluca' host='localhost' password='hiae'")
    dbConn.autocommit = True
except Exception as e:
    print "we can not connect to the database", e

tableDAO = TableDAO(dbConn)
geneDAO = GeneDAO(dbConn)
phenotypDAO = PhenotypDAO(dbConn)
phOnGeDAO = PhenotypOnGeneDAO(dbConn)

print "truncate all tables"
tableDAO.truncateAndResetIdentityFromAllTables()

print "Please wait, we are loading file in database... "

count = 0;
with open(hp_file_name, 'rb') as tsvin:
    next(tsvin) #skip file header
    tsvin =  csv.reader(tsvin, delimiter='\t')
    for row in tsvin:
        count += 1
        geneResult = geneDAO.selectGeneByIdentification(row[GENE_ID])
        if geneResult:
            ge_id = geneResult[0]
        else:
            ge_id = geneDAO.addNewGene(int(row[GENE_ID]), str(row[GENE_NAME]))

        phenotypeResult = phenotypDAO.selectPhenotypeByIdentification(row[HPO_ID])

        if phenotypeResult:
            ph_id = phenotypeResult[0]
        else:
            ph_id = phenotypDAO.addNewPhenotype(str(row[HPO_ID]), str(row[HPO_NAME]))

        phOnGeDAO.addNewPhenotypeOnGene(ph_id, ge_id);
        # print(count, ph_id, ge_id) # uncomment to "log"...
dbConn.close

print "File loading was finished"
print "END!"

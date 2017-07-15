import psycopg2, ast
from flask import Flask, jsonify
from flask_restful import Resource, Api
from phOnGeDAO import PhenotypOnGeneDAO

app = Flask(__name__)
api = Api(app)

GENE_ID = 0
GENE_NAME = 1
PHENOTYPE_ID = 2
PHENOTYPE_NAME = 3

class Phenotype(Resource):
    def get(self, list_of_gene_names):

        try:
            list_of_gene_names = ast.literal_eval(list_of_gene_names)
            set_of_gene_names = set(list_of_gene_names)
        except Exception as e:
            print e
            content = {'Something is wrong in request': "Verify the url, parameters and format."}
            return content, 400

        try:
            dbConn = psycopg2.connect("dbname='hiae-bioinfo' user='gianluca' host='localhost' password='hiae'")
            dbConn.autocommit = True
        except Exception as e:
            print "we can not connect to the database",e
            content = {'Ops!': "We are having a problem, please come back later."}
            return content, 500


        phenotypOnGeneDAO = PhenotypOnGeneDAO(dbConn)

        phenotypesListResult = []
        for geneName in set_of_gene_names:
            phenotypes = phenotypOnGeneDAO.selectPhenotypesByGeneName(geneName)
            for ph in phenotypes:
                phenotypesListResult.append({'pho':ph[PHENOTYPE_ID],'phenotype':ph[PHENOTYPE_NAME],'gene':ph[GENE_NAME],'gene_id':ph[GENE_ID]})

        dbConn.close

        return  jsonify(phenotypesListResult)


api.add_resource(Phenotype, '/phenotypes/genes/<list_of_gene_names>')

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')

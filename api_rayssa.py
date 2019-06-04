import psycopg2
from flask import Flask, request
from flask_restful import Resource, Api
import ast


app=Flask(__name__)
api=Api(app)

class Phenotypetogene(Resource):
    # define get function that will first collect the input list and then return the phenotyphes for each gene in the list
    def get(self,genes_list):

        # try to connect to postgres database, if error check given postgres settings
        # CHANGE NECESSARY POSTGRES SETTINGS EXCEPT DATABASE
        print "connecting to database"

        try:
            conn = psycopg2.connect("host=localhost user=postgres password=postgres port=5432 dbname=rayssa_bioinfo")

        except Exception:
            message={'Couldnt conenct to database': "please check given postgres settings"}
            return message
        #convert the string input into a dictionary and then into a list with its contents
        try:
            genes_list=ast.literal_eval(genes_list)
            genes_list=list(set(genes_list))

        except Exception as e:
            return e

        # CHANGE NECESSARY POSTGRES SETTINGS EXCEPT DATABASE
        conn = psycopg2.connect("host=localhost user=postgres password=postgres port=5432 dbname=rayssa_bioinfo")
        cur = conn.cursor()

        #search in the api_genes table the lines with the corresponding gene and print it in a dictionary format
        final_output=[]
        for gene in genes_list:
             cur.execute("SELECT*FROM api_genes where gene_name=%s", (gene,))
             data=cur.fetchall()
             for line in data:
                phenotype= {
                    "phenotype":[
                        {
                            "hpo": line[0],
                             "name": line[1]
                        }
                    ],
                        "gene": line[3],
                        "gene_id": line[2],
                }

                final_output.append(phenotype)

        return final_output

api.add_resource(Phenotypetogene,'/phenotypetogene/genes/<genes_list>')
if __name__ == '__main__':
    app.run(debug=True)

cur.close()
conn.close()
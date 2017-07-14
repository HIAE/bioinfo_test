class PhenotypOnGeneDAO:

    def __init__(self, dbConn):
        self.dbConn = dbConn


    def addNewPhenotypeOnGene(self, phId, geId):
        cur = self.dbConn.cursor()
        cur.execute("INSERT INTO ph_on_ge(phenotype_id, gene_id) VALUES (%s, %s) RETURNING id", (phId, geId,))
        return cur.fetchone()[0]

    def selectPhenotypesByGeneName(self, geneName):
        cur = self.dbConn.cursor()
        cur.execute("SELECT gene.ge_id, gene.ge_name, phenotype.ph_id, phenotype.ph_name FROM gene, phenotype, ph_on_ge where gene.ge_name = %s AND gene.id = ph_on_ge.gene_id AND ph_on_ge.phenotype_id = phenotype.id", (geneName,))
        return cur.fetchall()

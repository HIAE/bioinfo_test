class PhenotypDAO:

    def __init__(self, dbConn):
        self.dbConn = dbConn

    def selectPhenotypeByIdentification(self, phIdentification):
        cur = self.dbConn.cursor()
        cur.execute("SELECT * from phenotype WHERE ph_id = %s", (phIdentification,))
        return cur.fetchone()

    def addNewPhenotype(self, phIdentification, phName):
        cur = self.dbConn.cursor()
        cur.execute("INSERT INTO phenotype (ph_id, ph_name) VALUES (%s, %s) RETURNING id", (phIdentification, phName,))
        return cur.fetchone()[0]

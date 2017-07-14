class GeneDAO:

    def __init__(self, dbConn):
        self.dbConn = dbConn

    def selectGeneByIdentification(self, geIdentification):
        cur = self.dbConn.cursor()
        cur.execute("SELECT * from gene WHERE ge_id = %s", (geIdentification,))
        return cur.fetchone()

    def addNewGene(self, geIdentification, geName):
        cur = self.dbConn.cursor()
        cur.execute("INSERT INTO gene (ge_id, ge_name) VALUES (%s, %s) RETURNING id", (geIdentification, geName,))
        return cur.fetchone()[0]

class TableDAO:

    def __init__(self, dbConn):
        self.dbConn = dbConn

    def truncateAndResetIdentityFromAllTables(self):
        cur = self.dbConn.cursor()
        cur.execute("TRUNCATE gene, phenotype, ph_on_ge RESTART IDENTITY;")
        print("Truncate and reset identity from all tables")

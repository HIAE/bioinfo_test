import psycopg2

#try to creat to database rayssa_bioinfo, if error check given postgres settings
#CHANGE NECESSARY POSTGRES SETTINGS

try:
    conn = psycopg2.connect("host=localhost user=postgres password=postgres port=5432")
    conn.autocommit=True
    cur = conn.cursor()
    cur.execute("CREATE DATABASE rayssa_bioinfo")
    cur.close()
    conn.close()
except Exception:
    print "Couldnt create database: please check given postgres settings"
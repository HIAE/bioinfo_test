
-- É nescessário que exita a base de dados chamada hiae-bioinfo, usuario: gianluca, senha: hiae
-- db: hiae-bioinfo
-- user: gianluca
-- password: hiae

CREATE TABLE gene(
id SERIAL PRIMARY KEY,
ge_id INT NOT NULL,
ge_name VARCHAR(30) NOT NULL
);
CREATE INDEX ge_id_index ON gene (ge_id);
CREATE INDEX ge_name_index ON gene (ge_name);


CREATE TABLE phenotype(
id SERIAL PRIMARY KEY,
ph_id VARCHAR(50) NOT NULL,
ph_name VARCHAR(100) NOT NULL
);
CREATE INDEX ph_id_index ON phenotype (ph_id);

CREATE TABLE ph_on_ge(
id SERIAL PRIMARY KEY,
phenotype_id INT NOT NULL,
gene_id INT NOT NULL
);
CREATE INDEX gene_id_index ON ph_on_ge (gene_id);

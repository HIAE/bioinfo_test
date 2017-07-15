package br.com.hiae.bioinfo.dao;

import br.com.hiae.bioinfo.model.Gene;

public interface GeneDAO extends DAO<Gene> {
	Gene findByGeneName(String name);

}

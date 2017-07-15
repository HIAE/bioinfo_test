package br.com.hiae.bioinfo.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.hiae.bioinfo.model.Gene;

public class GeneDAOImpl extends DAOImpl<Gene> implements GeneDAO {
	
	@Inject
	public GeneDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	public Gene findByGeneName(String name) {
		TypedQuery<Gene> query = entityManager.createQuery("SELECT g FROM Gene g WHERE g.geneName=:geneName", Gene.class);
		return query.setParameter("geneName", name).getSingleResult();
	}


}

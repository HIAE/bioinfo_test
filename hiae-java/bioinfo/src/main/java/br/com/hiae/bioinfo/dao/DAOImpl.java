package br.com.hiae.bioinfo.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOImpl<T> implements DAO<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(DAOImpl.class);

	protected EntityManager entityManager;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public DAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	    this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return entityManager.createQuery("from " + entityClass.getName()).getResultList();
	}
	
	public T load(Long id) {
		if(id != null){
			return entityManager.find(entityClass, id);
		} else {
			return null;
		}
	}
	
	public void save(T entity) {
		entityManager.persist(entity);
	}
	
	public void refresh(T entity){
		entityManager.refresh(entity);
	}
	
	
	public void delete(T entity){
	  entityManager.remove(entity);
   }
	
	public T update(T entity){
		return entityManager.merge(entity);
	}
	
	public void flush(){
		entityManager.flush();
	}

	public T load(T entity) {
		Long idValue = 0L;
		try {
			idValue = (Long) entityClass.getMethod("getId").invoke(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
		return load(idValue);
	}

}

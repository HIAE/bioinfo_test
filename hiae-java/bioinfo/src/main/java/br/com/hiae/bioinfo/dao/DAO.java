package br.com.hiae.bioinfo.dao;

import java.util.List;


public interface DAO<T> {
	
	T load(Long id);
	T load(T entity);
	T update(T entity);
	void delete(T entity);
	void refresh(T entity);
	void flush();
	void save(T entity);
	List<T> findAll();
	
	

}

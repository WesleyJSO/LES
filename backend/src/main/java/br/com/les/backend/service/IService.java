package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService<T extends DomainEntity> {

	T save(T entity);
	
	T update(T entity);
	
	List<T> findByActive(T entity);

	List<T> findByInactive(T entity);
	
	boolean setActiveById(T entity);
	
	boolean setInactiveById(T entity);
	
	List<T> findByParameters(T entity);

	List<T> findAll(T entity);
	
	T findById(Long id, Class<? extends T> clazz);


}

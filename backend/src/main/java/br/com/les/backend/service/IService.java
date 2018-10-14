package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService<T extends DomainEntity> {

	public T save(T entity);
	
	public T update(T entity);
	
	public List<T> findByActive();
	
	public List<T> findByInactive();
	
	public boolean setActiveById(T entity);
	
	public boolean setInactiveById(T entity);
	
	public List<T> findByParameters(T entity);

	List<T> findAll();
	
	T findById(Long id, Class<? extends T> clazz);
}

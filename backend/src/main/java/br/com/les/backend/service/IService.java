package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService<T extends DomainEntity> {

	T save(T entity);
	
	T update(T entity);
	
	List<T> find(T entity);

	void delete(T entity);
}

package br.com.les.backend.dao;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IDAO<T extends DomainEntity> {

	T delete(T aEntity);
	
	T save(T aEntity);

	T update(T aEntity);

	List<T> find(T entity);	
}

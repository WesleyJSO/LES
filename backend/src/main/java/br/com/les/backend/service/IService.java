package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService<T extends DomainEntity> {

	T save( T entity );
	
	List< T > findAll();

	List< T > findByParameters( T entity );
	
	int softDelete( T entity );
}

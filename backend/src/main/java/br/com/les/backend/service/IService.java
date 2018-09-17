package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService {

	DomainEntity save( DomainEntity entity );
	
	<T extends DomainEntity> List< T > findAll();

	List< DomainEntity > findByParameters( DomainEntity entity );
	
<<<<<<< HEAD
	void delete(DomainEntity entity );
=======
	<T extends DomainEntity> int softDelete( T entity );
>>>>>>> 66dac4c6c26029578a47a257e36c811cc4afc1c3
}

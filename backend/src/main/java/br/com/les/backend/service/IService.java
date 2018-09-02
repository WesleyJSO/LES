package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService {

	DomainEntity save( DomainEntity entity );
	
	< T > List< T > findAll();

	List< DomainEntity > findByParameters( DomainEntity entity );
}

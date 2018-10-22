package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;


public interface IFacade<T extends DomainEntity> {

	void validate(T entity, String action);
	
    Result<T> save( T entity );

    Result<T> update( T entity );

    Result<T> delete( T entity );

    Result<T> find( T entity );
}

package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Result;


public interface IFacade<T extends DomainEntity> {

	void validate(T entity, String action, String callerMethod );
	
    Result<T> save( T entity, String callerMethod );

    Result<T> update( T entity, String callerMethod );

    Result<T> delete( T entity, String callerMethod );

    Result<T> findAll( T entity, String callerMethod );

    Result<T> find( T entity, String callerMethod );

	Result<T> findById(Long id, Class<? extends T> clazz);
}

package br.com.les.backend.facade;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.utils.Result;

public interface IApplicationFacade<T extends DomainEntity> {
	
	Result<T> save(T aEntity, INavigationCase<T> aCase);

	Result<T> update(T aEntity, INavigationCase<T> aCase);

	Result<T> delete(T aEntity, INavigationCase<T> aCase);
	
	Result<T> find(T aEntity, INavigationCase<T> aCase);

}

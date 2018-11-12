package br.com.les.backend.command;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.utils.Result;

@Component
public class GetCommand<T extends DomainEntity> extends AbstractCommand<T> {
	
	@SuppressWarnings("unchecked")
	@Override
	public Result<T> execute(T entity, INavigationCase<T> bCase) {
		return (Result<T>) applicationFacade.get(entity, bCase);
	}

}

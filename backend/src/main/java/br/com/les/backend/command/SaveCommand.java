package br.com.les.backend.command;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class SaveCommand<T extends DomainEntity> extends AbstractCommand<T> {
	
	@Override
	public Result<T> execute(T entity) {
		return facade.save( entity );
	}

}

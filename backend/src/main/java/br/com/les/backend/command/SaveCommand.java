package br.com.les.backend.command;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class SaveCommand extends AbstractCommand {
	
	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		return facade.save( entity, callerMethod );
	}

}

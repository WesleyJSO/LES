package br.com.les.backend.command;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface ICommand {
	
	public Result execute ( DomainEntity entity, String callerMethod);
	
}

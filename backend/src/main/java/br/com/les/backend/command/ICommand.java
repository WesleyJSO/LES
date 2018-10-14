package br.com.les.backend.command;


import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface ICommand<T extends DomainEntity> {
	
	public Result<T> execute (T entity, String callerMethod);
	
}

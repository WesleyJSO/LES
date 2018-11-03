package br.com.les.backend.navigator;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface INavigationCase<E extends DomainEntity> {

	public static final String DEFAULT_CONTEXT_NAME = "DEFAULT_CONTEXT";

	public String getName();

	public Result<E> getResult();

	public void suspendExecution();
	
	public void suspendExecution(String message);

	public Boolean isSuspendExecution();

	public void setContext(INavigatorContext context);

	public INavigatorContext getContext();

}

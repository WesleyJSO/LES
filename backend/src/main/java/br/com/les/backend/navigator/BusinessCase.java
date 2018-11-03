package br.com.les.backend.navigator;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public class BusinessCase<E extends DomainEntity> implements INavigationCase<E> {
	
	private Result<E> result;
	private String name;
	private Boolean suspend = false;
	private E entity;
	private INavigatorContext context;
	
	public BusinessCase() {
		this.result = new Result<>();
		this.context = new NavigatorContext();
	}

	public BusinessCase(String name) {
		this.name = name;
		this.result = new Result<>();
		this.context = new NavigatorContext();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Result<E> getResult() {
		return this.result;
	}

	@Override
	public void suspendExecution() {
		this.suspend = true;
		this.getResult().setError("Execução suspensa!");
	}

	@Override
	public Boolean isSuspendExecution() {
		return this.suspend;
	}

	public void setResult(Result<E> result) {
		this.result = result;
	}

	public void setName(String name) {
		this.name = name;
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	@Override
	public void setContext(INavigatorContext context) {
		if (this.context == null) {
			this.context = context;
			return;
		}
		this.context.setAttributes(context.getAttributes());
	}

	@Override
	public INavigatorContext getContext() {
		return context;
	}

	@Override
	public void suspendExecution(String message) {
		suspendExecution();
		result.setError(message);
	}

}

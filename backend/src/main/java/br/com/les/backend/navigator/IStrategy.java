package br.com.les.backend.navigator;

import br.com.les.backend.entity.DomainEntity;

public interface IStrategy<T extends DomainEntity> extends IEntity {

	public void process(T aEntity, INavigationCase<T> aCase);
}

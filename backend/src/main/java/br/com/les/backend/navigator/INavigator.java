package br.com.les.backend.navigator;

import br.com.les.backend.entity.DomainEntity;

public interface INavigator<E extends DomainEntity> extends IEntity {

	public void run(E aEntity, INavigationCase<E> aCase);

}

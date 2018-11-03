package br.com.les.backend.navigator;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.IStrategy;

public class NavigationBuilder<E extends DomainEntity> implements IEntity {

	private Navigation<E> navigation;

	public NavigationBuilder() {
		this.navigation = new Navigation<>();
	}

	public NavigationBuilder<E> next(IStrategy<E> activity) {
		navigation.addActivity(activity);
		return this;
	}

	public Navigation<E> build() {
		return navigation;
	}
}

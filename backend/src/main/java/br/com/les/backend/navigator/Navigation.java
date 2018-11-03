package br.com.les.backend.navigator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.IStrategy;

@Component
public class Navigation<E extends DomainEntity> implements IEntity {

	private List<IStrategy<E>> activities;

	public Navigation() {
		this.activities = new ArrayList<>();
	}

	public void addActivity(IStrategy<E> activity) {
		this.activities.add(activity);
	}

	public List<IStrategy<E>> getActivities() {
		return activities;
	}

}

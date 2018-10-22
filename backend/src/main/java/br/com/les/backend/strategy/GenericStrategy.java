package br.com.les.backend.strategy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class GenericStrategy<T extends DomainEntity> implements IStrategy<T> {

	@Autowired
	Map<String, IApplicationStrategy<T>> applicationStrategy;

	IApplicationStrategy<T> strategyToBeExecuted;
	
	@Override
	public Result<T> execute(T entity, String action) {
		
		applicationStrategy.forEach((k, v) -> {
			if(k.contains(entity.getClass().getSimpleName()) && k.contains(action.toLowerCase()))
				strategyToBeExecuted = v;
		});
		
		return strategyToBeExecuted.execute(entity);
	}
}

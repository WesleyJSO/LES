package br.com.les.backend.validator;

import com.google.common.base.Strings;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

public class ValidationForDelete<T extends DomainEntity> implements IStrategy<T> {
	
	@Override
	public void process(T aEntity, INavigationCase<T> aCase) {
		if (aEntity == null || Strings.isNullOrEmpty(aEntity.getId().toString()) || aEntity.getId() == 0) {
			aCase.suspendExecution();
			aCase.getResult().setError("Número do CNPJ não definido!");
			return;
		}
	}
}
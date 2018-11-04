package br.com.les.backend.validator.constcentre;

import com.google.common.base.Strings;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

public class VerifyNameAndCode implements IStrategy<CostCentre> {

	@Override
	public void process(CostCentre aEntity, INavigationCase<CostCentre> aCase) {
		
		if(aEntity != null) {
			if(Strings.isNullOrEmpty(aEntity.getName())) {
				aCase.suspendExecution();
				aCase.getResult().setError("Nome do centro de custo não foi definido!");
			}
			if(Strings.isNullOrEmpty(aEntity.getCode())) {
				aCase.suspendExecution();
				aCase.getResult().setError("Código do centro de custo não foi definido!");
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Centro de custo inexistente!");
		return;
	}
}

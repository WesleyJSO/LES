package br.com.les.backend.validator.parameter;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class SetIdNull implements IStrategy<Parameter> {
	
	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {

		if ( aEntity != null ) {

			aEntity.setId(null);
			return;
			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Parametro vazio!");
		return;
		
	}
	
}

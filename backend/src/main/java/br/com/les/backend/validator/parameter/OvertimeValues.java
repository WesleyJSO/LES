package br.com.les.backend.validator.parameter;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class OvertimeValues implements IStrategy<Parameter> {
	
	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {

		if ( aEntity != null ) {
			
			if ( aEntity.getOverTime() != null ) {
				
				if ( Strings.isNullOrEmpty( aEntity.getOverTime().getFirst() ) ) {
					
					aCase.suspendExecution();
					aCase.getResult().setError("Tipo Hora Extra vazio!");
				}
				return;

			}
			aCase.suspendExecution();
			aCase.getResult().setError("Tipo Hora Extra vazio!");
			return;
			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Parametro vazio!");
		return;
		
	}
	
}

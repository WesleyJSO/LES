package br.com.les.backend.strategy.parameter;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class CompTimeValues implements IStrategy<Parameter> {
	
	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {

		if ( aEntity != null ) {
			if ( aEntity.getCompTime() != null ) {

				if ( Strings.isNullOrEmpty( aEntity.getCompTime().getFirst() ) ) {
					
					aCase.suspendExecution();
					aCase.getResult().setError("Tipo Banco de Horas vazio!");
				}
				return;
			
			}
			aCase.suspendExecution();
			aCase.getResult().setError("Tipo Banco de Horas vazio!");
			return;
			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Parametro vazio!");
		return;
		
	}
	
}

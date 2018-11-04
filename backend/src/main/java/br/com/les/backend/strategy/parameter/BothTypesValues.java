package br.com.les.backend.strategy.parameter;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class BothTypesValues implements IStrategy<Parameter> {
	
	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {

		if ( aEntity != null ) {
			if ( aEntity.getBothTypes() != null ) {
				
				if ( Strings.isNullOrEmpty(aEntity.getBothTypes().getSecond()) ) {

					aCase.suspendExecution();
					aCase.getResult().setError("Segundo tipo aplicado não foi informado!");
					
				}

				if ( Strings.isNullOrEmpty(aEntity.getBothTypes().getFirst()) ) {

					aCase.suspendExecution();
					aCase.getResult().setError("Primeiro tipo aplicado não foi informado!");
					
				}
				return;
			
			}
			aCase.suspendExecution();
			aCase.getResult().setError("Tipo Hora Extra e Banco de Horas vazio!");
			return;
			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Parametro vazio!");
		return;
		
	}
	
}

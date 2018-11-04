package br.com.les.backend.strategy.parameter;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class VerifyDate implements IStrategy<Parameter> {
	
	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {

		if ( aEntity != null ) {

			if (!aEntity.getCreationDate().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
				aEntity.setEndDate(LocalDateTime.now().minusDays(1));
				aEntity.setActive(false);
			}
			return;
			
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Parametro vazio!");
		return;
		
	}
	
}

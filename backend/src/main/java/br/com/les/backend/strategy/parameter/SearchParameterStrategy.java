package br.com.les.backend.strategy.parameter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.utils.Result;

@Component
public class SearchParameterStrategy extends AbstractParameterStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {
		
		result = new Result();
		
		Parameter parameter = ( Parameter ) entity;
		
		if ( parameter != null ) {
			
			switch ( callerMethod ) {
			
				case "FindActive":
					
				break;
					
				default:
					break;
			}
		}
		return result;
	}

}

package br.com.les.backend.strategy.parameter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class SearchParameterStrategy implements IApplicationStrategy<Parameter> {

	@Override
	public Result<Parameter> execute(Parameter entity, String callerMethod) {
		
		Result<Parameter> result = new Result<>();
		
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

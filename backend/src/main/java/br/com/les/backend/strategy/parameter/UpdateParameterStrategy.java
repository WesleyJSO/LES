package br.com.les.backend.strategy.parameter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class UpdateParameterStrategy implements IApplicationStrategy<Parameter> {

	@Override
	public Result<Parameter> execute(Parameter entity) {

		Result<Parameter> result = new Result<>();
		
		Parameter parameter = ( Parameter ) entity;
	
		parameter.setEndDate(LocalDateTime.now().minusDays(1));
		parameter.setActive(false);
	
		result.setSuccess( Util.UPDATE_SUCESSFUL_PARAMETER );
		return result;
	}

}

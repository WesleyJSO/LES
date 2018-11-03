package br.com.les.backend.strategy.parameter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class UpdateParameterStrategy extends AbstractStrategy<Parameter> {
	
	@Autowired private SaveParameterStrategy strategy;

	@Override
	public Result<Parameter> execute(Parameter entity) {

		Result<Parameter> result = new Result<>();
		
		Parameter parameter = ( Parameter ) entity;
		
		if (!parameter.getCreationDate().toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
			parameter.setEndDate(LocalDateTime.now().minusDays(1));
			parameter.setActive(false);
		} else {
			Long id = parameter.getId();
			result = strategy.execute(parameter);
			parameter.setId(id);
		}
	
		result.setSuccess( Util.UPDATE_SUCESSFUL_PARAMETER );
		return result;
	}

}

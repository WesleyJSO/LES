package br.com.les.backend.strategy.parameter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class FindParameterStrategy extends AbstractStrategy<Parameter> {

	@Override
	public Result<Parameter> execute(Parameter entity) {
		
		Result<Parameter> result = new Result<>();
		return result;
	}

}

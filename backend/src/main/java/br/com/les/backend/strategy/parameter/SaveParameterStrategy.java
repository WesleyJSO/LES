package br.com.les.backend.strategy.parameter;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveParameterStrategy extends AbstractParameterStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {

		result = new Result();
		
		Parameter parameter = ( Parameter ) entity;
		switch ( callerMethod ) {
		case "Save":
			parameter.setId(null);
			parameter.setCreationDate();
		}
		result.setSuccess( Util.SAVE_SUCESSFUL_COMPANY );
		return result;
	}

}

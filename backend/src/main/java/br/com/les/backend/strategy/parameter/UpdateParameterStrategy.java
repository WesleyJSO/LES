package br.com.les.backend.strategy.parameter;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class UpdateParameterStrategy implements IApplicationStrategy<Parameter> {

	@Override
	public Result<Parameter> execute(Parameter entity, String callerMethod) {

		Result<Parameter> result = new Result<>();
		
		Parameter parameter = ( Parameter ) entity;
		switch ( callerMethod ) {
		case "UpdateSave":
			Calendar data = Calendar.getInstance();
			data.add(Calendar.DATE, -1);
			parameter.setEndDate(Calendar.getInstance());
			parameter.setActive(false);
		}
		result.setSuccess( Util.SAVE_SUCESSFUL_COMPANY );
		return result;
	}

}

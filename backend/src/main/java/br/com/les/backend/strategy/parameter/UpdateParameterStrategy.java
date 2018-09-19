package br.com.les.backend.strategy.parameter;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class UpdateParameterStrategy extends AbstractParameterStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {

		result = new Result();
		
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

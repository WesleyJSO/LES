package br.com.les.backend.strategy.request;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Request;
import br.com.les.backend.utils.RequestStatus;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveRequestStrategy extends AbstractRequestStrategy {

	@Override
	public Result run(DomainEntity entity, String callerMethod) {
		result = new Result();
		
		Request request = ( Request ) entity;
		// Take a look more carefully in how to get the request type
		RequestType type = Enum.valueOf( RequestType.class, String.valueOf( request.getType() ) );
		
		switch ( type ) {
		case APPOINTMENT:
			if( null == request.getStartDate() ) 
				result.setError("Inform uma data para a solicitação!!");
			if( null == request.getDescription() )
				result.setError("Informe uma descrição!!");
			if( null != request.getDescription() ) {
				if( request.getDescription().trim().equals("") )
					result.setError("Informe uma Descrição Válida!!" );
			}
			request.setStatus(RequestStatus.APPROVED);
			break;
		case ABSENSE_WITH_PERMISSION:
			break;
		case WORK_OVERTIME:
			break;
		case COMP_TIME: 
			break;
		case REALOCATION_DAYS:
			break;
		default:
			break;
		}
		
		if(result.isSuccess())
			result.setSuccess( Util.SAVE_SUCCESSFUL_REQUEST );
		return result;
	}

}

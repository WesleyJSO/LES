package br.com.les.backend.strategy.request;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Request;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.RequestStatus;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveRequestStrategy implements IApplicationStrategy<Request> {

	@Override
	public Result<Request> execute(Request entity, String callerMethod) {

		Result<Request> result = new Result<>();
		
		Request request = ( Request ) entity;
		// Take a look more carefully in how to get the request type
		RequestType type = Enum.valueOf( RequestType.class, String.valueOf( request.getType() ) );
		
		switch ( type ) {
		case CHANGE_APPOINTMENT:
			if( null == request.getStartDate() ) 
				result.setError(Util.ERROR_ENTRY_DATE);
			if( request.getStartDate().compareTo( Calendar.getInstance().getTime() ) <= 0 ) {
				result.setError(Util.INVALID_ENTRY_DATE);
			}
			if( null == request.getDescription() )
				result.setError(Util.ERROR_DESCRIPTION);
			if( null != request.getDescription() ) {
				if( request.getDescription().trim().equals("") )
					result.setError(Util.INVALID_DESCRIPTION);
			}
			request.setStatus(RequestStatus.SENT);
			break;
		case WORK_OVERTIME:
			if( null == request.getStartDate() ) 
				result.setError(Util.ERROR_ENTRY_DATE);
			if( request.getStartDate().compareTo( Calendar.getInstance().getTime() ) <= 0 ) {
				result.setError(Util.INVALID_ENTRY_DATE);
			}
			if( null != request.getEndDate() ) {
				if( request.getStartDate().compareTo(request.getEndDate()) <= 0 ) {
					result.setError(Util.INVALID_END_DATE);
				}
			}
			if( null == request.getDescription() )
				result.setError(Util.ERROR_DESCRIPTION);
			if( null != request.getDescription() ) {
				if( request.getDescription().trim().equals("") )
					result.setError(Util.INVALID_DESCRIPTION);
			}
			// Need to verify file uploaded
			break;
		case COMP_TIME:
			if( null == request.getStartDate() ) 
				result.setError(Util.ERROR_ENTRY_DATE);
			if( request.getStartDate().compareTo( Calendar.getInstance().getTime() ) <= 0 ) {
				result.setError(Util.INVALID_ENTRY_DATE);
			}
			if( request.getStartDate().compareTo(request.getEndDate()) <= 0 ) {
				result.setError(Util.INVALID_END_DATE);
			}
			if( null != request.getEndDate() ) {
				if( request.getStartDate().compareTo(request.getEndDate()) <= 0 ) {
					result.setError(Util.INVALID_END_DATE);
				}
			}
			if( null == request.getDescription() )
				result.setError(Util.ERROR_DESCRIPTION);
			if( null != request.getDescription() ) {
				if( request.getDescription().trim().equals("") )
					result.setError(Util.INVALID_DESCRIPTION);
			}
			break;
		case REALOCATION_DAYS:
			if( null == request.getStartDate() ) 
				result.setError(Util.ERROR_ENTRY_DATE);
			if( request.getStartDate().compareTo( Calendar.getInstance().getTime() ) <= 0 ) {
				result.setError(Util.INVALID_ENTRY_DATE);
			}
			if( request.getStartDate().compareTo(request.getEndDate()) <= 0 ) {
				result.setError(Util.INVALID_END_DATE);
			}
			if( request.getStartDate().compareTo(request.getEndDate()) <= 0 ) {
				result.setError(Util.INVALID_END_DATE);
			}
			if( null == request.getDescription() )
				result.setError(Util.ERROR_DESCRIPTION);
			if( null != request.getDescription() ) {
				if( request.getDescription().trim().equals("") )
					result.setError(Util.INVALID_DESCRIPTION);
			}
			
			break;
		default:
			break;
		}
		if(result.isSuccess())
			result.setSuccess( Util.SAVE_SUCCESSFUL_REQUEST );
		return result;
	}

}

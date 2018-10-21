package br.com.les.backend.strategy.request;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.les.backend.entity.Request;
import br.com.les.backend.service.RequestService;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.RequestStatus;
import br.com.les.backend.utils.RequestType;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

public class UpdateRequestStrategy implements IApplicationStrategy<Request> {

	@Autowired
	private RequestService service;

	@Override
	public Result<Request> execute(Request entity, String callerMethod) {

		Result<Request> result = new Result<>();

		Request request = (Request) entity;
		// Load request to persist changes from update
		Request savedRequest = service.findById(request.getId(), Request.class);
		// Take a look more carefully in how to get the request type
		RequestType type = Enum.valueOf(RequestType.class, String.valueOf(request.getType()));

		switch (type) {
		case CHANGE_APPOINTMENT:
			if (null == request.getStartDate())
				result.setError(Util.ERROR_ENTRY_DATE);
			else if (savedRequest.getStartDate().compareTo(request.getStartDate()) != 0)
				if(request.getStartDate().compareTo(Calendar.getInstance().getTime()) <= 0)
					result.setError(Util.INVALID_ENTRY_DATE);
			if (null == request.getDescription())
				result.setError(Util.ERROR_DESCRIPTION);
			else if (savedRequest.getDescription() != request.getDescription())
				if (request.getDescription().trim().equals("") || request.getDescription().length() < 10)
					result.setError(Util.INVALID_DESCRIPTION);
			break;
		case WORK_OVERTIME:
			if (null == request.getStartDate())
				result.setError(Util.ERROR_ENTRY_DATE);
			else if (savedRequest.getStartDate().compareTo(request.getStartDate()) != 0)
				if(request.getStartDate().compareTo(Calendar.getInstance().getTime()) <= 0)
					result.setError(Util.INVALID_ENTRY_DATE);
			if (null != request.getEndDate())
				if (request.getStartDate().compareTo(request.getEndDate()) <= 0)
					result.setError(Util.INVALID_END_DATE);
			if (null == request.getDescription())
				result.setError(Util.ERROR_DESCRIPTION);
			else if (savedRequest.getDescription() != request.getDescription())
				if (request.getDescription().trim().equals("") || request.getDescription().length() < 10)
					result.setError(Util.INVALID_DESCRIPTION);
			break;
		case COMP_TIME:
			if (null == request.getStartDate())
				result.setError(Util.ERROR_ENTRY_DATE);
			else if (savedRequest.getStartDate().compareTo(request.getStartDate()) != 0)
				if(request.getStartDate().compareTo(Calendar.getInstance().getTime()) <= 0)
					result.setError(Util.INVALID_ENTRY_DATE);
			if (null != request.getEndDate())
				if (request.getStartDate().compareTo(request.getEndDate()) <= 0)
					result.setError(Util.INVALID_END_DATE);
			if (null == request.getDescription())
				result.setError(Util.ERROR_DESCRIPTION);
			else if (savedRequest.getDescription() != request.getDescription())
				if (request.getDescription().trim().equals("") || request.getDescription().length() < 10)
					result.setError(Util.INVALID_DESCRIPTION);
			break;
		case REALOCATION_DAYS:
			if (null == request.getStartDate())
				result.setError(Util.ERROR_ENTRY_DATE);
			else if (savedRequest.getStartDate() != request.getStartDate())
				if(request.getStartDate().compareTo(Calendar.getInstance().getTime()) <= 0)
					result.setError(Util.INVALID_ENTRY_DATE);
			if (null != request.getEndDate())
				if (request.getStartDate().compareTo(request.getEndDate()) <= 0)
					result.setError(Util.INVALID_END_DATE);
			if(null == request.getEndDate())
				result.setError(Util.ERROR_END_DATE);
			else if(savedRequest.getEndDate().compareTo(request.getEndDate()) != 0)
				if (request.getStartDate().compareTo(request.getEndDate()) <= 0)
					result.setError(Util.INVALID_END_DATE);
			if (null == request.getDescription())
				result.setError(Util.ERROR_DESCRIPTION);
			else if (savedRequest.getDescription() != request.getDescription())
				if (request.getDescription().trim().equals("") || request.getDescription().length() < 10)
					result.setError(Util.INVALID_DESCRIPTION);
			break;
		default:
			break;
		}
		if (result.isSuccess())
			result.setSuccess(Util.UPDATE_SUCCESSFUL_REQUEST);
		return result;
	}

}
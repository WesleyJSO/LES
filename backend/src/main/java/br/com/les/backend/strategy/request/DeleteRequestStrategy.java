package br.com.les.backend.strategy.request;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Request;
import br.com.les.backend.service.EmployeeService;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

public class DeleteRequestStrategy implements IApplicationStrategy<Request> {

	@Autowired private EmployeeService service;
	@Override
	public Result<Request> execute(Request entity, String callerMethod) {

		Result<Request> result = new Result<>();
		
		Request request = ( Request ) entity;
		Employee employee = service.findById(request.getEmployee().getId(), Employee.class);
		if (!employee.getRoleList().contains("ROLE_ADM"))
			result.setError(Util.ERROR_PERMISSION_DENIED);
		
		return result;
	}
}

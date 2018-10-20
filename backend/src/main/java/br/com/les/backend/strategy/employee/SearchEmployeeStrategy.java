package br.com.les.backend.strategy.employee;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class SearchEmployeeStrategy implements IApplicationStrategy<Employee> {

	@Override
	public Result<Employee> execute( Employee employee, String callerMethod ) {
		
		Result<Employee> result = new Result<>();

		
		switch ( callerMethod ) {
		
		case "FindAll":
		break;

		default:
			break;
		}
		return result;
	}
}

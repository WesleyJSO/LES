package br.com.les.backend.strategy.employee;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class FindEmployeeStrategy extends AbstractStrategy<Employee> {

	@Override
	public Result<Employee> execute( Employee employee) {
		
		Result<Employee> result = new Result<>();

		return result;
	}
}

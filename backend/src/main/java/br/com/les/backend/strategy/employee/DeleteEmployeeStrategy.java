package br.com.les.backend.strategy.employee;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

public class DeleteEmployeeStrategy extends AbstractStrategy<Employee> {

	@Override
	public Result<Employee> execute(Employee entity) {

		return new Result<Employee>();
	}

}

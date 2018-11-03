package br.com.les.backend.strategy.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.service.SecurityService;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveEmployeeStrategy extends AbstractStrategy<Employee> {

	@Autowired SecurityService securityService;
	@Override
	public Result<Employee> execute(Employee employee) {
		
		Result<Employee> result = new Result<>();
		
		if( employee.getName() == null || employee.getName().isEmpty() )
			result.setError( Util.ERROR_NAME );
		
		if( employee.getLastName() == null || employee.getLastName().isEmpty() )
			result.setError( Util.ERROR_LAST_NAME );
		
		if( employee.getUser().getEmail() == null || employee.getUser().getEmail().isEmpty() )
			result.setError( Util.ERROR_EMAIL );
		
		if( employee.getBaseHourCalculation().getSalary() == 0 )
			result.setError(Util.ERROR_SALARY );
		
		if( employee.getPis() == null || employee.getPis().isEmpty() )
			result.setError( Util.ERROR_PIS );
		else
			if( employee.getPis().length() != 11 )
				result.setError( Util.INVALID_PIS);
			
		
		if( employee.getBaseHourCalculation().getWorkload() == 0 )
			result.setError( Util.ERROR_WORKLOAD );
		else
			if( employee.getBaseHourCalculation().getWorkload() >= 10)
				result.setError( Util.INVALID_WORKLOAD );
		
		if( employee.getManager().getName().equals("") )
			result.setError( Util.ERROR_MANAGER_NAME );
		
			
		if( employee.getJoiningDate().toString().isEmpty() ) {
				result.setError( Util.ERROR_JOINING_DATE );
		}
		
		if( employee.getUser().getPassword() == null || employee.getUser().getPassword().isEmpty() )
			result.setError( Util.INVALID_PASSWORD );
		
		if( result.isSuccess() )
			employee.getUser().setPassword( securityService.encodeUserPassword( employee.getUser().getPassword() ) );
			result.setSuccess( Util.SAVE_SUCCESSFUL_EMPLOYEE );
		
		return result;

	}
}

package br.com.les.backend.strategy.employee;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Telephone;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveEmployeeStrategy implements IApplicationStrategy<Employee> {

	@Override
	public Result<Employee> execute(Employee employee, String callerMethod ) {
		
		Result<Employee> result = new Result<>();
		switch ( callerMethod ) {
		case "Save":
			if( employee.getName() == null || employee.getName().isEmpty() )
				result.setError( Util.ERROR_NAME );
			
			if( employee.getLastName() == null || employee.getLastName().isEmpty() )
				result.setError( Util.ERROR_LAST_NAME );
			
			if( employee.getEmail() == null || employee.getEmail().isEmpty() )
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
			
			if( employee.getTelephoneList().isEmpty() )
				result.setError( Util.ERROR_PHONE );
			else {
				employee.getTelephoneList().forEach(Employee -> Employee.setUser(employee));
				
				for( Telephone telephone : employee.getTelephoneList() )
					if( telephone.getNumber().length() != 0 
						&& telephone.getNumber().length() != 8 
						&& telephone.getNumber().length() != 9 ) {
						result.setError( Util.INVALID_PHONE.concat( telephone.toString() ) );
						break;
					}
				
			}			
			if( employee.getJoiningDate().toString().isEmpty() ) {
					result.setError( Util.ERROR_JOINING_DATE );
			}
			
			if( employee.getPassword() == null || employee.getPassword().isEmpty() )
				result.setError( Util.INVALID_PASSWORD );
			
			if( result.isSuccess() )
				result.setSuccess( Util.SAVE_SUCCESSFUL_EMPLOYEE );
			
			break;
		}
		
		return result;

	}
}

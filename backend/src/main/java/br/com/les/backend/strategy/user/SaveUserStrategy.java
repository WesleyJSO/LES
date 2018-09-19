package br.com.les.backend.strategy.user;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Telephone;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveUserStrategy extends AbstractUserStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod ) {
		
		result = new Result();
		
		switch ( callerMethod ) {
		
		case "Save":
			
			Employee employee = ( Employee ) entity;
			
			if( employee.getName() == null || employee.getName().isEmpty() )
				result.setError( Util.ERROR_NAME );
			
			if( employee.getLastName() == null || employee.getLastName().isEmpty() )
				result.setError( Util.ERROR_LAST_NAME );
			
			if( employee.getEmail() == null || employee.getEmail().isEmpty() )
				result.setError( Util.ERROR_EMAIL );
			
			/*if( funcionario.getListaRole().isEmpty() )
				resultado.setError( "Ao menos uma role deve ser selecionada!" ); 
			*/
			if( employee.getBaseHourCalculation() == null )
				result.setError( Util.ERROR_SALARY );
			
			if( employee.getPis() == null || employee.getPis().isEmpty() )
				result.setError( Util.ERROR_PIS);
			else
				if( employee.getPis().length() < 11 )
					result.setError( Util.INVALID_PIS );
				
			
			if( employee.getBaseHourCalculation().getWorkload() == 0D )
				result.setError( Util.ERROR_WORKLOAD );
			
			if( employee.getManager().getName().equals("") )
				result.setError( Util.ERROR_MANAGER_NAME );
			
			if( employee.getTelephoneList().isEmpty() )
				result.setError( Util.ERROR_PHONE );
			else {
				
				for( Telephone telephone: employee.getTelephoneList() )
					if( telephone.getNumber().length() < 9 ) {
						result.setError( Util.INVALID_PHONE );
						break;
					}
				
			}
			
			if( employee.getLogin() == null )
				result.setError( Util.ERROR_LOGIN );
						
			if( employee.getJoiningDate() == null || employee.getJoiningDate().toString().isEmpty() )
				result.setError( Util.ERROR_JOINING_DATE );
			
			if( employee.getLogin().getPassword() == null || employee.getLogin().getPassword().isEmpty() )
				result.setError( Util.ERROR_PASSWORD );
			
			if( result.isSuccess() )
				result.setSuccess( Util.SAVE_SUCCESSFUL_USER );
			
			break;
		}
		
		return result;

	}
}

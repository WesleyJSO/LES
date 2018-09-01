package br.com.les.backend.strategy.user;

import java.util.Date;

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
		
		case "save":
			
			Employee employee = ( Employee ) entity;
			
			if( employee.getName() == null || employee.getName().isEmpty() )
				result.setErro( Util.ERROR_NAME );
			
			if( employee.getLastName() == null || employee.getLastName().isEmpty() )
				result.setErro( Util.ERROR_LAST_NAME );
			
			if( employee.getEmail() == null || employee.getEmail().isEmpty() )
				result.setErro( Util.ERROR_EMAIL );
			
			/*if( funcionario.getListaRole().isEmpty() )
				resultado.setErro( "Ao menos uma role deve ser selecionada!" ); 
			*/
			if( employee.getBaseCalculationHours() == null )
				result.setErro( Util.ERROR_SALARY );
			
			if( employee.getPis() == null || employee.getPis().isEmpty() )
				result.setErro( Util.ERROR_PIS);
			else
				if( employee.getPis().length() < 11 )
					result.setErro( Util.INVALID_PIS );
				
			
			if( employee.getBaseCalculationHours().getWorkload() == 0 )
				result.setErro( Util.ERROR_WORKLOAD );
			
			if( employee.getManager().getName().equals("") )
				result.setErro( Util.ERROR_MANAGER_NAME );
			
			if( employee.getThelephoneList().isEmpty() )
				result.setErro( Util.ERROR_PHONE );
			else {
				
				for( Telephone telephone: employee.getThelephoneList() )
					if( telephone.getNumber().length() < 9 ) {
						result.setErro( Util.INVALID_PHONE );
						break;
					}
				
			}
			
			if( employee.getLogin() == null )
				result.setErro( Util.ERROR_LOGIN );
			
			if( employee.getBirthDate() == null || employee.getBirthDate().toString().isEmpty() )
				result.setErro( Util.ERROR_BIRTHDATE );
			else {
				Date dataAtual = new Date();
				
				Long milliseconds = dataAtual.getTime() - employee.getBirthDate().getTime();
				
				if( milliseconds < 441504000000l ) {
					result.setErro( Util.INVALID_BIRTHDATE );
				}
			}
			
			if( employee.getEntryDateInCompany() == null || employee.getEntryDateInCompany().toString().isEmpty() )
				result.setErro( Util.ERROR_JOINING_DATE );
			
			if( employee.getLogin().getPassword() == null || employee.getLogin().getPassword().isEmpty() )
				result.setErro( Util.ERROR_PASSWORD );
			
			if( result.isSucesso() )
				result.setSucesso( Util.SAVE_SUCCESSFUL_USER );
			
			break;
		}
		
		return result;

	}
}

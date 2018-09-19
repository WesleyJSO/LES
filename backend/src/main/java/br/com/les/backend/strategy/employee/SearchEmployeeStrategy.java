package br.com.les.backend.strategy.employee;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Result;

@Component
public class SearchEmployeeStrategy extends AbstractEmployeeStrategy {

	@Override
	public Result execute( DomainEntity entity, String callerMethod ) {
		
		result = new Result();
		
		User u = ( User ) entity;
		
		if ( u != null ) {

			Login l = u.getLogin();
			
			switch ( callerMethod ) {
			
				case "FindAll":
					
				break;
				
				case "FindByUser": // search for list of users
					
					if ( l != null && l.getCreationDate() != null && u.getEmail() == null && u.getName() == null && u.getEmail() == null )
						result.setError( "Ao menos um dos campos deve ser preenchido!");
					break;
					
				case "FindByLogin": // search for logged user
					
							
					if ( l.getPassword() == null || l.getPassword().equals("") )
						result.setError( "Senha deve ser preenchida!" );
					
					break;
					
				default:
					break;
			}
		}
		return result;
	}
}

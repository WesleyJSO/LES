package br.com.les.backend.strategy.user;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Telephone;
import br.com.les.backend.entity.User;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveUserStrategy implements IApplicationStrategy<User> {

	@Override
	public Result<User> execute(User user, String callerMethod ) {
		
		Result<User> result = new Result<>();
		
		switch ( callerMethod ) {
		
		case "Save":
			
			if( user.getName() == null || user.getName().isEmpty() )
				result.setError( Util.ERROR_NAME );
			
			if( user.getLastName() == null || user.getLastName().isEmpty() )
				result.setError( Util.ERROR_LAST_NAME );
			
			if( user.getEmail() == null || user.getEmail().isEmpty() )
				result.setError( Util.ERROR_EMAIL );
			
			/*if( funcionario.getListaRole().isEmpty() )
				resultado.setError( "Ao menos uma role deve ser selecionada!" ); 
			*/
			/*if( user.getBaseHourCalculation() == null )
				result.setError( Util.ERROR_SALARY );
			
			if( user.getPis() == null || user.getPis().isEmpty() )
				result.setError( Util.ERROR_PIS);
			else
				if( user.getPis().length() < 11 )
					result.setError( Util.INVALID_PIS );
				
			
			if( user.getBaseHourCalculation().getWorkload() == 0D )
				result.setError( Util.ERROR_WORKLOAD );
			
			if( user.getManager().getName().equals("") )
				result.setError( Util.ERROR_MANAGER_NAME );*/
			
			if( user.getTelephoneList().isEmpty() )
				result.setError( Util.ERROR_PHONE );
			else {
				
				for( Telephone telephone: user.getTelephoneList() )
					if( telephone.getNumber().length() < 9 ) {
						result.setError( Util.INVALID_PHONE );
						break;
					}
				
			}
			/*if( user.getJoiningDate() == null || user.getJoiningDate().toString().isEmpty() )
				result.setError( Util.ERROR_JOINING_DATE );*/
			
			if( user.getPassword() == null || user.getPassword().isEmpty() )
				result.setError( Util.ERROR_PASSWORD );
			
			if( result.isSuccess() )
				result.setSuccess( Util.SAVE_SUCCESSFUL_USER );
			
			break;
		}
		
		return result;

	}
}

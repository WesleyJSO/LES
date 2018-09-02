package br.com.les.backend.strategy.user;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Result;

@Component
public class SearchUsuarioStrategy extends AbstractUserStrategy {

	@Override
	public Result execute( DomainEntity entity, String callerMethod ) {
		
		result = new Result();
		
		User u = ( User ) entity;
		
		if ( u != null ) {

			Login l = u.getLogin();
			
			switch ( callerMethod ) {
			
				case "findAll":
					
				break;
				
				case "findByUsuario": // search for list of users
					
					break;
					
				case "findByLogin": // search for logged user
					
					break;
					
				default:
		        break;
			}
		}
		return result;
	}
}

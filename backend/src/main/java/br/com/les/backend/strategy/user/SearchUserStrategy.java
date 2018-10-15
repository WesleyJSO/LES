package br.com.les.backend.strategy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.User;
import br.com.les.backend.service.UserService;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.EmailServiceImpl;
import br.com.les.backend.utils.Result;

@Component
public class SearchUserStrategy implements IApplicationStrategy<User> {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Override
	public Result<User> execute( User user, String callerMethod ) {
		
		Result<User> result = new Result<>();
		
			
			switch ( callerMethod ) {
			
				case "SentChangePasswordEmail":					
					if ( user.getEmail() == null || user.getEmail().isEmpty() ) {
						result.setError( "Informe um e-mail válido!" );
					} else {
//						List<User> list = userService.findByParameters(entity);
//						if ( !list.isEmpty() ) {
//							String generatedPassword = RandomStringUtils.randomAlphanumeric(10);
//							u = list.get( 0 );
//							u.getLogin().setPassword( generatedPassword );
//							userService.save( u );
//							emailService.sendEmail( u.getEmail(), "Alteração de senha", 
//									"Está é a sua nova senha de acesso temporário: " + u.getLogin().getPassword() );
//						}
					}
				break;
				
				case "FindAll":
					
				break;
				
				case "FindByUsuario": // search for list of users
					
					break;
					
				case "FindByLogin": // search for logged user
					
					break;
					
				default:
		        break;
			}
		return result;
	}
}

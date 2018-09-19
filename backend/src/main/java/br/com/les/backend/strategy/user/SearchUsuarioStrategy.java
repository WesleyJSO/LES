package br.com.les.backend.strategy.user;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.User;
import br.com.les.backend.service.UserService;
import br.com.les.backend.utils.EmailServiceImpl;
import br.com.les.backend.utils.Result;

@Component
public class SearchUsuarioStrategy extends AbstractUserStrategy {

	@Autowired
	UserService userService;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Override
	public Result execute( DomainEntity entity, String callerMethod ) {
		
		result = new Result();
		
		User u = ( User ) entity;
		
		if ( u != null ) {

			Login l = u.getLogin();
			
			switch ( callerMethod ) {
			
				case "SentChangePasswordEmail":					
					if ( u.getEmail() == null || u.getEmail().isEmpty() ) {
						result.setError( "Informe um e-mail válido!" );
					} else {
						List<DomainEntity> list = userService.findByParameters( (User) entity );
						if ( !list.isEmpty() ) {
							String generatedPassword = RandomStringUtils.randomAlphanumeric(10);
							u = (User) list.get( 0 );
							u.getLogin().setPassword( generatedPassword );
							userService.save( u );
							emailService.sendEmail( u.getEmail(), "Alteração de senha", 
									"Está é a sua nova senha de acesso temporário: " + u.getLogin().getPassword() );
						}
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
		}
		return result;
	}
}

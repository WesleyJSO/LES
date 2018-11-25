package br.com.les.backend.strategy.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.User;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.UserRepository;
import br.com.les.backend.service.SecurityService;

@Configuration
public class PasswordChange implements IStrategy <Employee> {
	

	@Autowired UserRepository userRepository;
	private final static Short PASSWORD_LENGTH = 3;
	
	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if ( aEntity != null && aEntity.getUser() != null ) {
			Optional<User> user = userRepository.findActiveById(aEntity.getUser().getId());
		
			if( !Strings.isNullOrEmpty( aEntity.getUser().getPassword() ) ) {
				if ( !SecurityService.checkMatchPassword(aEntity.getUser().getPassword(), user.get().getPassword() ) ) {
					// It's a new password
					// Verify it
					if(aEntity.getUser().getPassword().length() <= PASSWORD_LENGTH) 
						aCase.getResult().setError("Senha deve conter mais de ".concat(PASSWORD_LENGTH.toString()).concat(" caracteres!"));
					else
						aEntity.getUser().setPassword(SecurityService.encodeUserPassword( aEntity.getUser().getPassword() ) );
				}
			} else {
				// There is no change on password
				aEntity.getUser().setPassword(user.get().getPassword() );
			}
			
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Nenhuma senha informada!");
		return;
	}

}

package br.com.les.backend.strategy.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Telephone;
import br.com.les.backend.entity.User;
import br.com.les.backend.service.GenericService;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveUserStrategy implements IApplicationStrategy<User> {

	@Autowired private GenericService<User> userService;
	@Autowired private GenericService<Employee> employeeService;
	
	
	@Override
	public Result<User> execute(User user) {
		
		Result<User> result = new Result<>();
		
		if( user.getName() == null || user.getName().isEmpty() )
			result.setError( Util.ERROR_NAME );
		
		if( user.getLastName() == null || user.getLastName().isEmpty() )
			result.setError( Util.ERROR_LAST_NAME );
		
		if( user.getRoleList() == null || user.getRoleList().isEmpty() )
			result.setError(Util.ERROR_ROLE); 
		
		if(user.getTelephoneList() == null || user.getTelephoneList().isEmpty())
			result.setError(Util.ERROR_PHONE);
		else
			result = validatePhoneNumbers(result, user.getTelephoneList());
			
		if( user.getEmail() == null || user.getEmail().isEmpty() )
			result.setError( Util.ERROR_EMAIL );
		else
			result = verifyIfEmailIsAlreadyUsed(result, user);
		
		if( user.getPassword() == null || user.getPassword().isEmpty() )
			result.setError( Util.ERROR_PASSWORD );
		
		if( result.isSuccess() )
			result.setSuccess( Util.SAVE_SUCCESSFUL_USER );
		
		return result;
	}

	private Result<User> validatePhoneNumbers(Result<User> result, List<Telephone> telephoneList) {
		for (int i = telephoneList.size() - 1; i >= 0; i--)
			if(telephoneList.get(i).getNumber() == null || telephoneList.get(i).getNumber() == "")
				telephoneList.remove(i);
			else if(telephoneList.get(i).getNumber().length() != 8 
					&& telephoneList.get(i).getNumber().length() != 9) 
				result.setError(Util.INVALID_PHONE.concat(telephoneList.get(i).getNumber()));		
		return result;
	}

	private Result<User> verifyIfEmailIsAlreadyUsed(Result<User> result, User user) {
		List<User> searchUserResult = null; 
		User u = new User();
		u.setEmail(user.getEmail());
		searchUserResult = userService.find(u);
		if(!searchUserResult.isEmpty())
			result.setError(Util.ERROR_ALREADY_REGISTRED_EMAIL);
		else {
			List<Employee> searchEmployeeResult = null;
			Employee e = new Employee();
			e.setEmail(user.getEmail());
			searchEmployeeResult = employeeService.find(e);
			if(!searchEmployeeResult.isEmpty())
				result.setError(Util.ERROR_ALREADY_REGISTRED_EMAIL);
		}
		return result;
	}
}

package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.employee.EmailExistence;
import br.com.les.backend.strategy.employee.EmailFormat;
import br.com.les.backend.strategy.employee.IdActivate;
import br.com.les.backend.strategy.employee.IdExistence;
import br.com.les.backend.strategy.employee.IdInactivate;
import br.com.les.backend.strategy.employee.NameValidator;
import br.com.les.backend.strategy.employee.NewUserConfig;
import br.com.les.backend.strategy.employee.PasswordLength;
import br.com.les.backend.strategy.employee.PisLength;
import br.com.les.backend.strategy.employee.SalaryValidator;
import br.com.les.backend.strategy.employee.TelephoneExistence;
import br.com.les.backend.strategy.employee.WorkLoadLimit;


@Configuration
public class EmployeeNavigation {

	@Autowired private EmailFormat emailFormat;
	@Autowired private EmailExistence emailExistence;
	@Autowired private PasswordLength passwordLength;
	@Autowired private TelephoneExistence telephoneExistence;
	@Autowired private WorkLoadLimit workLoadLimit;
	@Autowired private NameValidator nameValidator;
	@Autowired private PisLength pisLength;
	@Autowired private SalaryValidator salaryValidator;
	@Autowired private IdExistence idExistence;
	@Autowired private IdActivate idActivate;
	@Autowired private IdInactivate idInactivate;
	@Autowired private NewUserConfig newUserConfig;
	
	@Bean("ACTIVATE_EMPLOYEE")
	public Navigation<Employee> activateEmployeeValidator() {
		return new NavigationBuilder<Employee>()
				.next(idExistence)
				.next(idActivate)
				.build();
	}
	
	@Bean("INACTIVATE_EMPLOYEE")
	public Navigation<Employee> inactivateEmployeeValidator() {
		return new NavigationBuilder<Employee>()
				.next(idExistence)
				.next(idInactivate)
				.build();
	}
	
	@Bean("DELETE_EMPLOYEE")
	public Navigation<Employee> deleteEmployeeValidator() {
		return new NavigationBuilder<Employee>()
				.next(idExistence)
				.build();
	}
	
	@Bean("FIND_EMPLOYEE")
	public Navigation<Employee> findEmployeeValidator() {
		return new NavigationBuilder<Employee>()
				.build();
	}
	
	@Bean("SAVE_EMPLOYEE")
	public Navigation<Employee> saveEmployeeValidator() {
		return new NavigationBuilder<Employee>()
				.next(emailFormat)
				.next(emailExistence)
				.next(passwordLength)
				.next(telephoneExistence)
				.next(workLoadLimit)
				.next(nameValidator)
				.next(pisLength)
				.next(salaryValidator)
				.next(newUserConfig)
				.build();
	}
	
	@Bean("UPDATE_EMPLOYEE")
	public Navigation<Employee> updateEmployeeValidator() {
		return new NavigationBuilder<Employee>()
				.next(emailFormat)
				.next(emailExistence)
				.next(passwordLength)
				.next(telephoneExistence)
				.next(workLoadLimit)
				.next(nameValidator)
				.next(pisLength)
				.next(salaryValidator)
				.build();
	}
}

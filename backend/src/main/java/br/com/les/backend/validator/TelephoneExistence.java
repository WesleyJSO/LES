package br.com.les.backend.validator;

import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Telephone;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.utils.Util;

@Configuration
public class TelephoneExistence implements IStrategy<Employee> {

	@Override
	public void process(Employee aEntity, INavigationCase<Employee> aCase) {

		if (aEntity != null && aEntity.getTelephoneList() != null) {
		
			aEntity.getTelephoneList().forEach(e -> e.setUser(aEntity));
			
			for( Telephone telephone : aEntity.getTelephoneList() )
				if( telephone.getNumber().length() != 0 
					&& telephone.getNumber().length() != 8 
					&& telephone.getNumber().length() != 9 ) {

					aCase.suspendExecution();
					aCase.getResult().setError(Util.INVALID_PHONE.concat(telephone.toString()));
					break;
				}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Nenhum telefone foi informado!");
		return;
	}

}

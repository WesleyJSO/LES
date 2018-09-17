package br.com.les.backend.strategy.Company;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@Component
public class DeleteCompanyStrategy extends AbstractCompanyStrategy {
	
	public Result execute(DomainEntity entity, String callerMethod) {
		
		result = new Result();
		
		Company c = new Company();
		switch ( callerMethod ) {
		case "delete":
			break;
		}
		return result;
	}

}

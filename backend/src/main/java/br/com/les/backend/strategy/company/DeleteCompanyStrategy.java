package br.com.les.backend.strategy.company;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteCompanyStrategy implements IApplicationStrategy<Company> {
	
	public Result<Company> execute(Company entity, String callerMethod) {
		
		Result<Company> result = new Result<>();
		
		switch ( callerMethod ) {
		case "Delete":
			break;
		}
		return result;
	}

}

package br.com.les.backend.strategy.company;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class DeleteCompanyStrategy extends AbstractStrategy<Company> {
	
	public Result<Company> execute(Company entity) {
		
		Result<Company> result = new Result<>();
		
		return result;
	}

}

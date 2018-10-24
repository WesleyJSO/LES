package br.com.les.backend.strategy.company;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.strategy.AbstractStrategy;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class FindCompanyStrategy extends AbstractStrategy<Company> {

	@Override
	public Result<Company> execute(Company entity) {

		Result<Company> result = new Result<>();
		
		result.setSuccess( Util.SEARCH_SUCESSFUL_COMPANY );
		return result;
	}

}

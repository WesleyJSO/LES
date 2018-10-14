package br.com.les.backend.strategy.company;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SearchCompanyStrategy implements IApplicationStrategy<Company> {

	@Override
	public Result<Company> execute(Company entity, String callerMethod) {

		Result<Company> result = new Result<>();
		
		switch ( callerMethod ) {
		case "Search":
			
			break;
		}
		result.setSuccess( Util.SEARCH_SUCESSFUL_COMPANY );
		return result;
	}

}

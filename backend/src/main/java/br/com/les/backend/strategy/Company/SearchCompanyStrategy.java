package br.com.les.backend.strategy.company;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.company.AbstractCompanyStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SearchCompanyStrategy extends AbstractCompanyStrategy {

	@Override
	public Result execute(DomainEntity entity, String callerMethod) {

		result = new Result();
		
		Company c = ( Company ) entity;
		
		switch ( callerMethod ) {
		case "search":
			
			break;
		}
		result.setSuccess( Util.SEARCH_SUCESSFUL_COMPANY );
		return result;
	}

}

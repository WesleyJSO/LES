package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.company.ICompanyStrategy;
import br.com.les.backend.utils.Result;

@Component( "Company" )
public class CompanyStrategy implements IStrategy {

	@Autowired
	List< ICompanyStrategy > actionStrategies;
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {

		ICompanyStrategy strategyToBeExecuted = null;
		
		for ( ICompanyStrategy s : actionStrategies ) 
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
				
		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}

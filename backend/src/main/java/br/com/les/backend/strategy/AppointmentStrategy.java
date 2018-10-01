package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.strategy.appointment.IAppointmentStrategy;
import br.com.les.backend.utils.Result;

@Component( "Appointment" )
public class AppointmentStrategy implements IStrategy {

	@Autowired
	List< IAppointmentStrategy > actionStrategies;
	
	@Override
	public Result execute( DomainEntity entity, String action, String callerMethod ) {

		IAppointmentStrategy strategyToBeExecuted = null;
		
		for ( IAppointmentStrategy s : actionStrategies ) 
			if ( s.getClass().getSimpleName().contains( action ) )
				strategyToBeExecuted = s;
				
		return strategyToBeExecuted.execute( entity, callerMethod );
	}

}

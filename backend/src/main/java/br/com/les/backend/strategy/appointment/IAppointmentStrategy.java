package br.com.les.backend.strategy.appointment;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

public interface IAppointmentStrategy {

	Result execute( DomainEntity entity, String callerMethod );

}

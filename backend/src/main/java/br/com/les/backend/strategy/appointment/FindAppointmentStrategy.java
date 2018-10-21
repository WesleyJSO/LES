package br.com.les.backend.strategy.appointment;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;

@Component
public class FindAppointmentStrategy implements IApplicationStrategy<Appointment> {

	@Override
	public Result<Appointment> execute(Appointment appointment) {
		
		Result<Appointment> result = new Result<>();
		
		return result;
	}

}

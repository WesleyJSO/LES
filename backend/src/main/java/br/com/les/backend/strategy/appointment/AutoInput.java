package br.com.les.backend.strategy.appointment;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.AppointmentRequest;
import br.com.les.backend.entity.RequestStatus;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.AppointmentRequestRepository;

@Configuration
public class AutoInput implements IStrategy<Appointment> {
	
	@Autowired AppointmentRequestRepository appointmentRequestRepository;

	@Override
	public void process(Appointment aEntity, INavigationCase<Appointment> aCase) {

		if (aEntity != null) {
			
			// the system should not auto input any value if there's 
			// an appointment request in progress to this appointment
			List<AppointmentRequest> requests = null;
			requests = appointmentRequestRepository.findEvaluatedByAppointmentId(aEntity.getId(), RequestStatus.SENT.getValue());
			if ( !requests.isEmpty() ) {
				return;
			}

			if ( null != aEntity.getAfternoonOut() ) {
				if ( null != aEntity.getMorningEntrance() 
						&& null == aEntity.getMorningOut()
						&& null == aEntity.getAfternoonEntrance()) {
					aEntity.setMorningOut( LocalTime.NOON );
					aEntity.setAfternoonEntrance( LocalTime.NOON );
				}
			}
			
			if ( null != aEntity.getNightOut() ) {
				if ( null != aEntity.getAfternoonEntrance() ) { 
					if ( null == aEntity.getAfternoonOut()
						&& null == aEntity.getNightEntrance()) {
						aEntity.setAfternoonOut(LocalTime.of(18, 0));
						aEntity.setNightEntrance(LocalTime.of(18, 0));
					}
				} else if ( null != aEntity.getMorningEntrance() 
						&& null == aEntity.getMorningOut()
						&& null == aEntity.getAfternoonOut()
						&& null == aEntity.getNightEntrance()) {
					aEntity.setMorningOut( LocalTime.NOON );
					aEntity.setAfternoonEntrance( LocalTime.NOON );
					aEntity.setAfternoonOut(LocalTime.of(18, 0));
					aEntity.setNightEntrance(LocalTime.of(18, 0));					
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Apontamento necessário para realização do cálculo inexistente!");
		return;
	}
}

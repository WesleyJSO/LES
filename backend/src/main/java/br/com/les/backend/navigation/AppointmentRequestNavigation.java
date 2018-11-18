package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.AppointmentRequest;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.appointment.UpdateChangeSpecifcAppointment;

@Configuration
public class AppointmentRequestNavigation {

	@Autowired UpdateChangeSpecifcAppointment updateChangeSpecifcAppointment;
	
	@Bean("FIND_APPOINTMENTREQUEST")
	public Navigation<AppointmentRequest> findValidator() {
		return new NavigationBuilder<AppointmentRequest>().build();
	}

	@Bean("SAVE_APPOINTMENTREQUEST")
	public Navigation<AppointmentRequest> saveAppointmentRequestValidator() {
		return new NavigationBuilder<AppointmentRequest>().build();
	}

	@Bean("UPDATE_APPOINTMENTREQUEST")
	public Navigation<AppointmentRequest> updateAppointmentRequestValidator() {
		return new NavigationBuilder<AppointmentRequest>()
				.next(updateChangeSpecifcAppointment)
				.build();
	}

	@Bean("DELETE_APPOINTMENTREQUEST")
	public Navigation<AppointmentRequest> deleteAppointmentRequestValidator() {
		return new NavigationBuilder<AppointmentRequest>().build();
	}

}

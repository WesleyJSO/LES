package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.validator.appointment.CalculateBalance;
import br.com.les.backend.validator.appointment.ChangeSpecificAppointment;

@Configuration
public class AppointmentNavigation {
	@Autowired private ChangeSpecificAppointment changeSpecificAppointment;
	@Autowired private CalculateBalance calculateBalance;
	
	@Bean("FIND_APPOINTMENT")
	public Navigation<Appointment> findAppointmentValidator() {
		return new NavigationBuilder<Appointment>().build();
	}

	@Bean("SAVE_APPOINTMENT")
	public Navigation<Appointment> saveAppointmentValidator() {
		return new NavigationBuilder<Appointment>()
				.next(calculateBalance)
				.build();
	}

	@Bean("UPDATE_APPOINTMENT")
	public Navigation<Appointment> updateAppointmentValidator() {
		return new NavigationBuilder<Appointment>()
				.next(changeSpecificAppointment)
				.next(calculateBalance)
				.build();
	}

	@Bean("DELETE_APPOINTMENT")
	public Navigation<Appointment> deleteAppointmentValidator() {
		return new NavigationBuilder<Appointment>().build();
	}

}

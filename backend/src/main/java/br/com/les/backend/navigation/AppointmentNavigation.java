package br.com.les.backend.navigation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;

@Configuration
public class AppointmentNavigation {

	@Bean("FIND_APPOINTMENT")
	public Navigation<Appointment> findAppointmentValidator() {
		return new NavigationBuilder<Appointment>().build();
	}

	@Bean("SAVE_APPOINTMENT")
	public Navigation<Appointment> saveAppointmentValidator() {
		return new NavigationBuilder<Appointment>().build();
	}

	@Bean("UPDATE_APPOINTMENT")
	public Navigation<Appointment> updateAppointmentValidator() {
		return new NavigationBuilder<Appointment>().build();
	}

	@Bean("DELETE_APPOINTMENT")
	public Navigation<Appointment> deleteAppointmentValidator() {
		return new NavigationBuilder<Appointment>().build();
	}

}

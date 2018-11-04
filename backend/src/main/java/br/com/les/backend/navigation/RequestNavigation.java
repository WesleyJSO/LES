package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Request;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.validator.request.RequestToChangeAppointment;
import br.com.les.backend.validator.request.RequestToCompTime;
import br.com.les.backend.validator.request.RequestToRealocateWorkDay;
import br.com.les.backend.validator.request.RequestToSaveAppointment;
import br.com.les.backend.validator.request.RequestToSaveCompTime;
import br.com.les.backend.validator.request.RequestToSaveRealocateDay;
import br.com.les.backend.validator.request.RequestToSaveWorkOvertime;
import br.com.les.backend.validator.request.RequestToWorkOvertime;

@Configuration
public class RequestNavigation {

	@Autowired private RequestToRealocateWorkDay requestToRealocateWorkDay; 
	@Autowired private RequestToCompTime requestToCompTime;
	@Autowired private RequestToWorkOvertime requestToWorkOvertime;
	@Autowired private RequestToChangeAppointment requestToChangeAppointment;
	@Autowired private RequestToSaveAppointment requestToSaveAppointment;
	@Autowired private RequestToSaveWorkOvertime requestToSaveWorkOvertime;
	@Autowired private RequestToSaveCompTime requestoToSaveCompTime;
	@Autowired private RequestToSaveRealocateDay requestToSaveRealocateDay;
	
	@Bean("FIND_REQUEST")
	public Navigation<Request> findRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

	@Bean("SAVE_REQUEST")
	public Navigation<Request> saveequestValidator() {
		return new NavigationBuilder<Request>()
				.next(requestToSaveAppointment)
				.next(requestToSaveWorkOvertime)
				.next(requestoToSaveCompTime)
				.next(requestToSaveRealocateDay)
				.build();
	}

	@Bean("UPDATE_REQUEST")
	public Navigation<Request> updateRequestValidator() {
		return new NavigationBuilder<Request>()
				.next(requestToChangeAppointment)
				.next(requestToCompTime)
				.next(requestToRealocateWorkDay)
				.next(requestToWorkOvertime)
				.build();
	}

	@Bean("DELETE_REQUEST")
	public Navigation<Request> deleteRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

}

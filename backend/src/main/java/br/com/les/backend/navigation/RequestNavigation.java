package br.com.les.backend.navigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Request;
import br.com.les.backend.navigator.Navigation;
import br.com.les.backend.navigator.NavigationBuilder;
import br.com.les.backend.strategy.request.ApproveRequestCompTime;
import br.com.les.backend.strategy.request.ApproveRequestRealocateDay;
import br.com.les.backend.strategy.request.ApproveRequestWorkOvertime;
import br.com.les.backend.strategy.request.EditRequesChangeAppointment;
import br.com.les.backend.strategy.request.EditRequestCompTime;
import br.com.les.backend.strategy.request.EditRequestRealocateWorkDay;
import br.com.les.backend.strategy.request.RequestToSaveAppointment;
import br.com.les.backend.strategy.request.RequestCompTime;
import br.com.les.backend.strategy.request.RequestRealocateDay;
import br.com.les.backend.strategy.request.RequestWorkOvertime;
import br.com.les.backend.strategy.request.EditRequestWorkOvertime;
import br.com.les.backend.strategy.request.FindRequest;

@Configuration
public class RequestNavigation {

	@Autowired private EditRequestRealocateWorkDay requestToRealocateWorkDay; 
	@Autowired private EditRequestCompTime requestToCompTime;
	@Autowired private EditRequestWorkOvertime requestToWorkOvertime;
	@Autowired private EditRequesChangeAppointment requestToChangeAppointment;
	@Autowired private RequestToSaveAppointment requestToSaveAppointment;
	@Autowired private RequestWorkOvertime requestToSaveWorkOvertime;
	@Autowired private RequestCompTime requestoToSaveCompTime;
	@Autowired private RequestRealocateDay requestToSaveRealocateDay;
	@Autowired private ApproveRequestCompTime approveRequestComptime;
	@Autowired private ApproveRequestRealocateDay approveRequestRealocateDay;
	@Autowired private ApproveRequestWorkOvertime approveRequestWorkOvertime;
	@Autowired private FindRequest findRequest;
	
	@Bean("FIND_REQUEST")
	public Navigation<Request> findRequestValidator() {
		return new NavigationBuilder<Request>()
				.next(findRequest)
				.build();
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
				.next(approveRequestComptime)
				.next(approveRequestRealocateDay)
				.next(approveRequestWorkOvertime)
				.build();
	}

	@Bean("DELETE_REQUEST")
	public Navigation<Request> deleteRequestValidator() {
		return new NavigationBuilder<Request>().build();
	}

}

package br.com.les.backend.dao;

import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.filter.ChartFilter;

@Service
@Transactional
public class FilterDAO extends GenericDAO<ChartFilter>{

	private static DateTimeFormatter monthYearPettern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String s = "";
	private ChartFilter chartFilter;
	
	@SuppressWarnings("unchecked")
	public ChartFilter get(ChartFilter filter) {
		
		chartFilter = new ChartFilter();
		
		s = "select * from appointment a join employee e on a.employee_id=e.id where ";
		if(filter.getEmployeeNameList() != null && !filter.getEmployeeNameList().isEmpty()) {
			s = s.concat("e.id in(");
			filter.getEmployeeNameList().forEach(i -> s = s.concat(i).concat(", ") );
			s = s.substring(0, s.length() - 2);
			s = s.concat(") and ");
		} 
		if(filter.getInitialQueryDate() != null) {
			s = s.concat(" date >='");
			s = s.concat(filter.getInitialQueryDate().format(monthYearPettern));
		}
		if(!s.endsWith("where ") && filter.getFinalQueryDate() != null) {
			s = s.concat("' and ");
		}
		if(filter.getFinalQueryDate() != null) {			
			s = s.concat(" date <='");
			s = s.concat(filter.getFinalQueryDate().format(monthYearPettern));
			s = s.concat("'");
		}
		if(s.endsWith("where "))
			return null;
		else
			chartFilter.setAppointmentList(em.createNativeQuery(s, Appointment.class).getResultList());
			return chartFilter;
	}
}

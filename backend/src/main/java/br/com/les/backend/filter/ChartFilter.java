package br.com.les.backend.filter;

import java.time.LocalDate;
import java.util.List;


import org.springframework.stereotype.Component;

import br.com.les.backend.annotation.DateQuery;
import br.com.les.backend.annotation.ListQuery;
import br.com.les.backend.annotation.Query;
import br.com.les.backend.entity.DomainEntity;

@Component
@Query("Appointment")
public class ChartFilter extends DomainEntity {

	private List<String> employeeNameList;
	private LocalDate initialQueryDate;
	private LocalDate finalQueryDate;
	
	@ListQuery(name="t.employee.name") 
	public List<String> getEmployeeNameList() {
		return employeeNameList;
	}

	public void setEmployeeNameList(List<String> employeeNameList) {
		this.employeeNameList = employeeNameList;
	}
	
	@DateQuery(name="t.date") 
	public LocalDate getInitialQueryDate() {
		return initialQueryDate;
	}

	public void setInitialQueryDate(LocalDate initialQueryDate) {
		this.initialQueryDate = initialQueryDate;
	}

	@DateQuery(name="t.date", comparator=" <= '") 
	public LocalDate getFinalQueryDate() {
		return finalQueryDate;
	}

	public void setFinalQueryDate(LocalDate finalQueryDate) {
		this.finalQueryDate = finalQueryDate;
	}

}

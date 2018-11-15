package br.com.les.backend.strategy.dashboard;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.dao.DashboardFilterDAO;
import br.com.les.backend.entity.BankedHours;
import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.filter.DashboardFilter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.BankedHoursRepository;
import br.com.les.backend.repository.ParameterRepository;

@Configuration
public class CreateDashboardData implements IStrategy<DashboardFilter> {
	
	@Autowired ParameterRepository parameterRepository;
	@Autowired DashboardFilterDAO dashboardFilterDAO;
	@Autowired BankedHoursRepository bankedHoursRepository;
	
	@Override
	public void process(DashboardFilter aEntity, INavigationCase<DashboardFilter> aCase) {

		List<Parameter> parameterList = parameterRepository.findAllActive();
		Parameter parameter = parameterList.get(0);
		
		int limitMonth = parameter.getBankCompensationLimitTime();
		
		LocalDate limitDate = LocalDate.now().minusMonths(limitMonth);
				
		List<MonthlyBalance> m = dashboardFilterDAO.findDashboardData(limitDate);
		
		m.sort((m1, m2) -> {
			if(!m1.getEmployee().getId().equals(m2.getEmployee().getId()))
				return m1.getEmployee().getId().compareTo(m2.getEmployee().getId());
			else
				return m1.getEmployee().getId().compareTo(m2.getEmployee().getId());
		});
		
		boolean exit = false;
		long empId = 0;
		DashboardFilter d = null;
		List<DashboardFilter> dashboardFilterList = new ArrayList<>();
		for(int current = 0; current < m.size(); current++) {
			for(int next = current + 1; next <= m.size(); current++) {
				
				double balance;
				double balanceMinutes = 0;
				// new employee, select his company time hour balance
				if(empId != m.get(current).getEmployee().getId()) {
					Optional<BankedHours> bankedHours = bankedHoursRepository.findActiveById(m.get(current).getEmployee().getId());
					
					if(bankedHours.isPresent()) {
						balance = bankedHours.get().getBalance();
						balanceMinutes = balance * 60;
						balanceMinutes += (balance - (int)balance * 60); // total para subtrair
						empId = bankedHours.get().getEmployee().getId();						
					}
					// dashboardFilterList.add(d);
				}
				
				int monthWorkload = m.get(current).getMonthWorkload() * 60;
				int totalMinutes = m.get(current).getBalanceMinutes() + m.get(current).getBalanceHours() * 60;
				int monthBalance = totalMinutes - monthWorkload;
				
				int nextMonthWorkload = m.get(next).getMonthWorkload() * 60;
				int nextTotalMinutes = m.get(next).getBalanceMinutes() + m.get(next).getBalanceHours() * 60;
				int nextMonthBalance = nextTotalMinutes - nextMonthWorkload;
				
				if((monthBalance > 0 && nextMonthBalance < 0) || (monthBalance < 0 && nextMonthBalance > 0)) {
					m.get(current).setMonthWorkload(0);
					m.get(current).setBalanceMinutes(0);
					
					int minutesLeft = monthBalance + nextMonthBalance;
					
					int finalHours = minutesLeft / 60;
					int finalMinutes = finalHours * 60 - minutesLeft;
					
					m.get(next).setBalanceHours(finalHours);
					m.get(next).setBalanceMinutes(finalMinutes);
					
					break;
				}
				
				d = new DashboardFilter();			
				d.setEmployee(m.get(current).getEmployee().getName().concat(" ").concat(m.get(current).getEmployee().getLastName()));
				// d.setWorkedHourComercial(m.get(current).wor workedHourComercial);
				
				d.setBalance(String
						.valueOf(balanceMinutes / 60)
						.concat(":")
						.concat(String.valueOf((int)(balanceMinutes - (balanceMinutes /60)))));
				
				d.setLastMonth(limitDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
				
//				d.setHoursInLastMonth();
//				d.setWorkedHourComercial();
//				d.setWorkedHourNight();
				
				limitDate.plusMonths(1);
			}
		}
	
		if(aEntity != null) {

		}
		aCase.suspendExecution();
		aCase.getResult().setError("Erro ao gerar dashboard!");
		return;
	}
}

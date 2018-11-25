package br.com.les.backend.strategy.dashboard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.dao.DashboardFilterDAO;
import br.com.les.backend.entity.BankedHours;
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.filter.DashboardFilter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.BankedHoursRepository;
import br.com.les.backend.repository.ParameterRepository;

@Configuration
public class CreateDashboardData implements IStrategy<DashboardFilter> {
	
	@Autowired BankedHoursRepository bankedHoursRepository;
	@Autowired ParameterRepository parameterRepository;
	@Autowired DashboardFilterDAO dashboardFilterDAO;
	
	private List<MonthlyBalance> m = new ArrayList<>();
	private List<DashboardFilter> dashboardFilterList;
	private DashboardFilter employeeDashboardData;
	private LocalDate limitDate;
	private long employeeId = 0;
	private boolean theLastBalanceForThisUserHasEvaluated = false;
	
	@Override
	public void process(DashboardFilter aEntity, INavigationCase<DashboardFilter> aCase) {
 
		dashboardFilterList = new ArrayList<>();
		searchEveryBalanceWithinParameterLimit(aCase);
		
		if(aCase.getResult().isSuccess()) {
			
			calculateBalance();
			sortMonthlyBalanceList();
			logInConsole();
			m.forEach(monthlyBalance -> {
				
				if(employeeId != monthlyBalance.getEmployee().getId()) {
					theLastBalanceForThisUserHasEvaluated = false;
					employeeId = monthlyBalance.getEmployee().getId();
					employeeDashboardData = new DashboardFilter();
					employeeDashboardData.setEmployeeEvaluatedBalanceList(new ArrayList<>());
					dashboardFilterList.add(employeeDashboardData);
				}
				employeeDashboardData.getEmployeeEvaluatedBalanceList().add(monthlyBalance);
				
				if(monthlyBalance.getAbscenseHours() != 0 || monthlyBalance.getAbscenseMinutes() != 0 
				|| monthlyBalance.getOvertimeHours() != 0 || monthlyBalance.getOvertimeMinutes() != 0) {
					
					// this is this last month which the balance have some time
					if(!theLastBalanceForThisUserHasEvaluated) {
						
						employeeDashboardData.setEmployeeFullName(fetchUserFullName(monthlyBalance));
						
						employeeDashboardData.setHoursInLastMonth(fetchHoursInLastMonth(monthlyBalance));
						employeeDashboardData.setHoursInLastMonthMinutes(fetchLastMonthHoursInMinutes(monthlyBalance));

						employeeDashboardData.setLastMonth(fetchLastMonthWhitBalance(monthlyBalance, aCase));
						employeeDashboardData.setLastMonthNumber(fetchMonthNumber(monthlyBalance));
						
						employeeDashboardData.setBalance(fetchBalance(monthlyBalance));
						employeeDashboardData.setBalanceInMinutes(fetchBalanceInMinutes(monthlyBalance));
						
						if(!Strings.isNullOrEmpty(employeeDashboardData.getHoursInLastMonth()) 
								&& !"0:00".equals(employeeDashboardData.getHoursInLastMonth())) {
							theLastBalanceForThisUserHasEvaluated = true;
						}
									
					}
										
					// still not have this data
					/*employeeDashboardData.setWorkedHourComercial();
						employeeDashboardData.setWorkedHourNight();*/
				}
				employeeDashboardData.setCountingFromMonth(fetchWhichMonthIsTheFirstToCountInParameters());
				employeeDashboardData.setCountingFromMonthNumber(fetchMonthNumber(null));
				
				employeeDashboardData
					.setShouldBeWorked(fetchHoursThatWasNeedToBeWorkedInPeriod(monthlyBalance,
						employeeDashboardData.getShouldBeWorked()));
				
				employeeDashboardData
					.setWorkedHoursOnTheParameterPeriod(fetchWorkedHours(monthlyBalance, 
						employeeDashboardData.getWorkedHoursOnTheParameterPeriod()));
				
				employeeDashboardData
					.setWorkedHoursOnTheParameterPeriodMinutes(fetchWorkedHoursInMinutes(monthlyBalance,
						employeeDashboardData.getWorkedHoursOnTheParameterPeriodMinutes()));
			});
			aCase.getResult().setResultList(dashboardFilterList);
		}
	}

	private String fetchWhichMonthIsTheFirstToCountInParameters() {
		return limitDate.format(DateTimeFormatter.ofPattern("MM/yyyy")).toString();
	}
	
	private String formatHoursToString(long minutes) {
		String minute = String.valueOf(minutes - minutes / 60 * 60).replace("-", "");
		if(minute.length() < 2)
			minute = "0".concat(minute);
		return  String.valueOf(minutes < 0 && minutes > -60 ? "-0" : minutes / 60).concat(":").concat(minute);
	}
	
	private String fetchBalance(MonthlyBalance monthlyBalance) {
		Optional<BankedHours> bankedHours = bankedHoursRepository.findByEmployeeId(monthlyBalance.getEmployee().getId());
		if(bankedHours.isPresent()) {
			Double balance = bankedHours.get().getBalance();
			return formatHoursToString(balance.intValue() * 60 + (int)((balance - balance.intValue()) * 60));
		}
		return null;
	}

	private Long fetchBalanceInMinutes(MonthlyBalance monthlyBalance) {
		Optional<BankedHours> bankedHours = bankedHoursRepository.findByEmployeeId(monthlyBalance.getEmployee().getId());
		if(bankedHours.isPresent()) {
			Double balance = bankedHours.get().getBalance();
			return (long) (balance.intValue() * 60 + (int)((balance - balance.intValue()) * 60));
		}
		return null;
	}
	
	private String fetchHoursThatWasNeedToBeWorkedInPeriod(MonthlyBalance monthlyBalance, String shouldBeWorked) {
		long minutes = 0;
		if(!Strings.isNullOrEmpty(shouldBeWorked)) {
			minutes = Integer.parseInt(shouldBeWorked.substring(0, shouldBeWorked.indexOf(":"))) * 60
					+ Integer.parseInt(shouldBeWorked.substring(shouldBeWorked.indexOf(":") + 1, shouldBeWorked.length()));
		}
		return formatHoursToString(monthlyBalance.getMonthWorkload() * 60 + minutes); 
	}
	
	private String fetchWorkedHours(MonthlyBalance monthlyBalance, String previousValue) {
		long minutes = 0;
		if(!Strings.isNullOrEmpty(previousValue)) {
			minutes = Integer.parseInt(previousValue.substring(0, previousValue.indexOf(":"))) * 60
					+ Integer.parseInt(previousValue.substring(previousValue.indexOf(":") + 1, previousValue.length()));
		}
		return formatHoursToString(monthlyBalance.getBalanceHours() * 60 + monthlyBalance.getBalanceMinutes() + minutes);
	}

	private Long fetchWorkedHoursInMinutes(MonthlyBalance monthlyBalance, Long previousValue) {
		return monthlyBalance.getBalanceHours() * 60 + monthlyBalance.getBalanceMinutes() + (previousValue != null ? previousValue : 0); 
	}
	
	private String fetchHoursInLastMonth(MonthlyBalance monthlyBalance) {
		return formatHoursToString((monthlyBalance.getOvertimeHours() * 60 + monthlyBalance.getOvertimeMinutes())
				- (monthlyBalance.getAbscenseHours() * 60 + monthlyBalance.getAbscenseMinutes()));
	}

	private Long fetchLastMonthHoursInMinutes(MonthlyBalance monthlyBalance) {
		return (long) ((monthlyBalance.getOvertimeHours() * 60 + monthlyBalance.getOvertimeMinutes())
				- (monthlyBalance.getAbscenseHours() * 60 + monthlyBalance.getAbscenseMinutes()));
	}
	
	private String fetchLastMonthWhitBalance(MonthlyBalance monthlyBalance, INavigationCase<DashboardFilter> aCase) {
		return monthlyBalance.getMonthAndYear().getMonth().getDisplayName(TextStyle.FULL, new Locale("pt"));
	}
	
	private Short fetchMonthNumber(MonthlyBalance monthlyBalance) {
		if(monthlyBalance != null)
			return (short) (monthlyBalance.getMonthAndYear().getMonth().ordinal() + 1);		
		return (short) (limitDate.getMonth().ordinal() + 1);
	}
	
	private String fetchUserFullName(MonthlyBalance monthlyBalance) {
		return monthlyBalance.getEmployee().getName()
				.concat(" ").concat(monthlyBalance.getEmployee().getLastName());
	}

	private void calculateBalance() {
		long empId = 0;
		for(short current = 0; current < m.size(); current++) {
			
			long currentEmployeeId = m.get(current).getEmployee().getId();
			for(short next = current; next < m.size(); next++) {				
				
				if(currentEmployeeId != m.get(next).getEmployee().getId())
					break;
				
				if(empId != currentEmployeeId)
					empId = currentEmployeeId;	
				
				int absenceHours = m.get(current).getAbscenseHours() * 60 + m.get(current).getAbscenseMinutes();
				if(absenceHours == 0)
					break;
				int overtimeHours = m.get(next).getOvertimeHours() * 60 + m.get(next).getOvertimeMinutes();
				
				applicateNewValues(m, current, next, absenceHours, overtimeHours);
			}
			
			for(short next = current; next < m.size(); next++) {
				
				if(currentEmployeeId != m.get(next).getEmployee().getId())
					break;
				
				if(empId != currentEmployeeId)
					empId = currentEmployeeId;	
				
				
				int overtimeHours = m.get(current).getOvertimeHours() * 60 + m.get(current).getOvertimeMinutes();
				if(overtimeHours == 0)
					break;
				int absenceHours = m.get(next).getAbscenseHours() * 60 + m.get(next).getAbscenseMinutes();
				
				applicateNewValues(m, next, current, absenceHours, overtimeHours);
			}
		}
	}

	private void logInConsole() {
		m.forEach(i -> {
			System.out.println(i.getEmployee().getId() 
					+ ": " 
					+ i.getMonthAndYear() 
					+ ", abscenseHours:" + i.getAbscenseHours() 
					+ ", abscenseMinutes: " + i.getAbscenseMinutes()
					+ ", overtimeHours: " + i.getOvertimeHours()
					+ ", overtimeMinutes: " + i.getOvertimeMinutes());
		});		
	}

	/**
	 * Fetches every monthly balance that fits the data within the 
	 * non compensatory limit parameter for every active employee, 
	 * then sort the values based on employee and the month
	 * @param aCase 
	 */
	private void searchEveryBalanceWithinParameterLimit(INavigationCase<DashboardFilter> aCase) {
		
		List<Parameter> parameterList = parameterRepository.findAllActive();
		if(parameterList != null && !parameterList.isEmpty()) {
			int limitMonth = parameterList.get(0).getBankCompensationLimitTime();
		
			limitDate = LocalDate.now().minusMonths(limitMonth).withDayOfMonth(1);
				
			m = dashboardFilterDAO.findDashboardData(limitDate);
			
			sortMonthlyBalanceList();
			
		} else {
			aCase.suspendExecution();
			aCase.getResult().setError("Erro durante consulta de parâmetros!");
		}
	}

	private void sortMonthlyBalanceList() {
		m.sort((m1, m2) -> {
			if(!m1.getEmployee().getId().equals(m2.getEmployee().getId()))
				return m1.getEmployee().getId().compareTo(m2.getEmployee().getId());
			else
				return m1.getMonthAndYear().compareTo(m2.getMonthAndYear());
		});
	}

	private void applicateNewValues(List<MonthlyBalance> m, short firstMonth,
			short nextMonth, int absence, int overtime) {
		
		int minutesLeft = 0;
		if(absence > overtime) {
			minutesLeft =  absence - overtime;
			m.get(nextMonth).setOvertimeHours(0);
			m.get(nextMonth).setOvertimeMinutes(0);
			m.get(firstMonth).setAbscenseHours(minutesLeft / 60); 
			m.get(firstMonth).setAbscenseMinutes(minutesLeft - minutesLeft / 60 * 60);
		} else if(absence < overtime) {			
			minutesLeft =  overtime - absence;
			m.get(firstMonth).setAbscenseHours(0);
			m.get(firstMonth).setAbscenseMinutes(0);
			m.get(nextMonth).setOvertimeHours(minutesLeft / 60);
			m.get(nextMonth).setOvertimeMinutes(minutesLeft - minutesLeft / 60 * 60);			
		} else {
			m.get(firstMonth).setAbscenseHours(0);
			m.get(firstMonth).setAbscenseMinutes(0);
			m.get(nextMonth).setOvertimeHours(0);
			m.get(nextMonth).setOvertimeMinutes(0);			
		}
	}
}

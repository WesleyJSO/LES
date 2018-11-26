package br.com.les.backend.helper;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.MonthlyBalance;

public class BankStatementHelper {

	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private String companyName;
	private String CNPJ;
	private String employeeName;
	private String admissionDate;
	private String lastMonthBalance;
	private String finalBalance;	
	private String month;
	private List<BalanceHelper> balanceList;
	
	public BankStatementHelper generateHelper(Employee employee, MonthlyBalance lastmb, 
			MonthlyBalance currentmb, LocalDate month, List<Appointment> appointList) {
		this.companyName = "Empresa Fictícia LTDA";
		this.CNPJ = "CNPJ: 95.248.143/0006-16";
		this.employeeName = employee.getName() + " " + employee.getLastName();
		LocalDate date = employee.getJoiningDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.admissionDate = date.format(dateFormatter);
		this.lastMonthBalance = "00:00";
		this.finalBalance = "00:00";
		this.month = month.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt"));
		
		balanceList = new ArrayList<>();
		BalanceHelper balanceHelper = null;
		Appointment appointment = null;
		LocalDate daysCount = null;
		for ( daysCount = month.with(firstDayOfMonth()); 
				daysCount.isBefore(month.with(firstDayOfMonth()).plusMonths(1)); 
				daysCount = daysCount.plusDays(1)) {
			balanceHelper = new BalanceHelper();
			LocalDateTime dateTime = LocalDateTime.of(daysCount, LocalTime.MIDNIGHT);
			appointment = appointList.stream()
					.filter(a -> a.getDate().equals(dateTime))
					.findAny().orElse(null);
			if ( null == appointment) {
				balanceHelper = balanceHelper.generateHelper(dateTime.getDayOfMonth(), LocalTime.MIDNIGHT, LocalTime.MIDNIGHT);
			} else {
				balanceHelper = balanceHelper.generateHelper(dateTime.getDayOfMonth(), appointment.getHoursLeft(), appointment.getDayOvertime());
			}
			balanceList.add(balanceHelper);
		}
		
		balanceHelper = new BalanceHelper();
		balanceHelper = balanceHelper.generateHelper(0, 
				LocalTime.of(currentmb.getAbscenseHours(), currentmb.getAbscenseMinutes()), 
				LocalTime.of(currentmb.getOvertimeHours(), currentmb.getOvertimeMinutes()));
		balanceHelper.setDay("TOTAL");
		balanceList.add(balanceHelper);

		return this;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getLastMonthBalance() {
		return lastMonthBalance;
	}
	public void setLastMonthBalance(String lastMonthBalance) {
		this.lastMonthBalance = lastMonthBalance;
	}
	public String getFinalBalance() {
		return finalBalance;
	}
	public void setFinalBalance(String finalBalance) {
		this.finalBalance = finalBalance;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<BalanceHelper> getBalanceList() {
		return balanceList;
	}
	public void setBalanceList(List<BalanceHelper> balanceList) {
		this.balanceList = balanceList;
	}
	
	
}

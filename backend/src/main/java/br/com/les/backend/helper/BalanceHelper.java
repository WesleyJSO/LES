package br.com.les.backend.helper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BalanceHelper {
	
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	private String day;
	private String debt;
	private String credit;
	
	public BalanceHelper generateHelper(Integer day, LocalTime debit, LocalTime credit) {
		this.day = day.toString();
		this.debt = debit.format(timeFormatter);
		this.credit = credit.format(timeFormatter);
		return this;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getDebt() {
		return debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}

	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	

}

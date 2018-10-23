package br.com.les.backend.strategy.appointment;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.HistMonthlyBalanceChange;
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.repository.AppointmentRepository;
import br.com.les.backend.repository.MonthlyBalanceRepository;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
@Component
public class UpdateAppointmentStrategy implements IApplicationStrategy<Appointment> {

	@Autowired
	@Qualifier("appointmentRepository")
	private AppointmentRepository appointmentRepository;

	@Autowired
	@Qualifier("monthlyBalanceRepository")
	MonthlyBalanceRepository monthlyBalanceRepository;
	
	@Override
	public Result<Appointment> execute(Appointment a) {

		Result<Appointment> result = new Result<>();
		SaveAppointmentStrategy strategy = new SaveAppointmentStrategy();
		result = strategy.execute(a);
		
		List<MonthlyBalance> monthlyBalanceList = new ArrayList<>();
		
		// get all the appointments created after the edited appointment
		List< Appointment > list = appointmentRepository.findByDate(a.getDate().toLocalDate().atStartOfDay());
		
		// using the history of 
		for (Appointment ap : list){
			for (HistMonthlyBalanceChange hist : ap.getHistChangeMonthlyBalanceList()) {
				if ( !monthlyBalanceList.contains(hist.getMonthlyBalance()) ) {
					monthlyBalanceList.add(hist.getMonthlyBalance());
				}
				for ( MonthlyBalance mb : monthlyBalanceList ) {
					if ( mb.getMonth().equals(hist.getMonthlyBalance().getMonth()) ) {
						mb.setBalance( mb.getBalance() + (hist.getAmount() * (-1d)) );
					}
				}
			}
		}
		
		if ( monthlyBalanceList.isEmpty() ) {
			monthlyBalanceList = monthlyBalanceRepository.findWithNoBalance();
		}
		
		for ( Appointment ap : list ) {
			if ( ap.getDayOvertime().isAfter(LocalTime.MIN) ) {
				
			} else if ( ap.getHoursLeft().isAfter(LocalTime.MIN) ) {
//			if ( monthlyBalanceList.get(0).getBalance() < ap.get )
			}
		}
		return result;
	}
	
	public Double getDoubleTime(LocalTime time) {

		Double min = Double.valueOf( time.getMinute() / 60);
		Double hor = time.getHour() + min;
		return hor;
	}

}

package br.com.les.backend.strategy.timetracking;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.entity.TimeTracking;
import br.com.les.backend.helper.AppointmentHelper;
import br.com.les.backend.helper.TimeTrackingHelper;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.EmployeeRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;

@Configuration
public class GenerateReport implements IStrategy<TimeTracking> {

	@Autowired EmployeeRepository employeeRepository;
	@Autowired GenericDAO<MonthlyBalance> monthlyBalanceDAO;
	@Autowired GenericDAO<Appointment> appointmentDAO;
	
	@Override
	public void process(TimeTracking aEntity, INavigationCase<TimeTracking> aCase) {
		if (aEntity != null) {
			try {

				List<MonthlyBalance> list = new ArrayList<MonthlyBalance>();
				
				Employee employee = employeeRepository.findActiveById(aEntity.getEmployeeId()).get();
				
				Employee emp = new Employee();
				emp.setId(employee.getId());
				
				MonthlyBalance monthlyBalance = new MonthlyBalance();
				monthlyBalance.setMonthAndYear(aEntity.getMonth());
				monthlyBalance.setEmployee(emp);
				list = monthlyBalanceDAO.find(monthlyBalance);
				if ( !list.isEmpty() ) {
					monthlyBalance = list.get(0);
					list.clear();
				}
				
				Appointment appointment = new Appointment();
				appointment.setMonthAndYear(aEntity.getMonth());
				appointment.setEmployee(emp);
				List<Appointment> appointmentList = appointmentDAO.find(appointment);
				
				if ( appointmentList.isEmpty() ) {
					aCase.suspendExecution();
					aCase.getResult().setError("Nenhum apontamento encontrado!");
					return;
				}
				
				TimeTrackingHelper helper = new TimeTrackingHelper();
				helper = helper.generateHelper(aEntity.getMonth(), appointmentList,
						employee.getBaseHourCalculation(), monthlyBalance, employee);
				
				List<AppointmentHelper> appHelperList = helper.getAppointmentList();

	            // Setting parameters
	            Map<String, Object> parametro = new HashMap<>();
	            
	            parametro.put( "prmInitDate" , helper.getInitDate());
	            parametro.put( "prmEndDate" , helper.getEndDate());
	            parametro.put( "prmWorkingHours" , helper.getWorkingHours());
	            parametro.put( "prmBalance" , helper.getBalance());
	            parametro.put( "prmAbsence" , helper.getAbsence());
	            parametro.put( "prmOvertime" , helper.getOvertime());
	            parametro.put( "prmMonthBalance" , helper.getMonthlyBalance());
	            parametro.put( "prmEmployeeName" , helper.getEmployeeName());
	            parametro.put( "APPOINTMENT_BEAN" , appHelperList);
				
				// Generating jasper file if it doesn't exists
	            if ( !new File("reports/timeTracking.jasper").exists() ) {
		            JasperReport relatorio = JasperCompileManager.compileReport( "reports/timeTracking.jrxml" );
		            JRSaver.saveObject(relatorio, "reports/timeTracking.jasper");
	            }

	            // fill the report with data
	            JasperFillManager.fillReportToFile("reports/timeTracking.jasper", parametro, new JRBeanCollectionDataSource( appHelperList ));
				// Save PDF in reports folder
				JasperExportManager.exportReportToPdfFile("reports/TimeTracking.jrprint");
	        } catch ( Exception ex ) {
	            ex.printStackTrace();
	        }
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Erro!");
		return;
	}
}

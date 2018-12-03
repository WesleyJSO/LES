package br.com.les.backend.strategy.bankstatement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.BankStatement;
import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.helper.BalanceHelper;
import br.com.les.backend.helper.BankStatementHelper;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.CompanyRepository;
import br.com.les.backend.repository.EmployeeRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;

@Configuration
public class GenerateStatement implements IStrategy<BankStatement> {

	@Autowired CompanyRepository companyRepository;
	@Autowired EmployeeRepository employeeRepository;
	@Autowired GenericDAO<MonthlyBalance> monthlyBalanceDAO;
	@Autowired GenericDAO<Appointment> appointmentDAO;
	
	@Override
	public void process(BankStatement aEntity, INavigationCase<BankStatement> aCase) {
		if (aEntity != null) {
			try {
				
				List<MonthlyBalance> list = new ArrayList<MonthlyBalance>();
				
				Employee employee = employeeRepository.findActiveById(aEntity.getEmployeeId()).get();
				
				Employee emp = new Employee();
				emp.setId(employee.getId());
				
				MonthlyBalance lastMonthBalance = new MonthlyBalance();
				lastMonthBalance.setMonthAndYear(aEntity.getMonth().minusMonths(1));
				lastMonthBalance.setEmployee(emp);
				list = monthlyBalanceDAO.find(lastMonthBalance);
				if ( !list.isEmpty() ) {
					lastMonthBalance = monthlyBalanceDAO.find(lastMonthBalance).get(0);
					list.clear();
				}
				
				MonthlyBalance currentMonthBalance = new MonthlyBalance();
				currentMonthBalance.setMonthAndYear(aEntity.getMonth());
				currentMonthBalance.setEmployee(emp);
				list = monthlyBalanceDAO.find(currentMonthBalance);
				if ( !list.isEmpty() ) {
					currentMonthBalance = list.get(0);
					list.clear();
				}
				
				Appointment appointment = new Appointment();
				appointment.setMonthAndYear(aEntity.getMonth());
				appointment.setEmployee(emp);
				List<Appointment> appointmentList = appointmentDAO.find(appointment);
				
				List<Company> companyList = companyRepository.findAll();
				Company company = new Company();
				if ( !companyList.isEmpty() ) {
					company = companyList.get(0);
					list.clear();
				}
				
				BankStatementHelper helper = new BankStatementHelper();
				helper = helper.generateHelper(employee, lastMonthBalance, currentMonthBalance, 
						aEntity.getMonth(), appointmentList);
				
				List<BalanceHelper> balanceHelperList = helper.getBalanceList();

	            // Setting parameters
	            Map<String, Object> parametro = new HashMap<>();
	            
	            if ( null != company.getTradingName() ) {
	            	parametro.put( "prmCompanyName" , company.getTradingName() );
	            } else {
	            	parametro.put( "prmCompanyName" , helper.getCompanyName() );
	            }
	            if ( null != company.getCnpj() ) {
	            	try {
		            	MaskFormatter mask = new MaskFormatter("###.###.###/####-##");
		            	mask.setValueContainsLiteralCharacters(false);
		            	parametro.put( "prmCNPJ" , mask.valueToString(company.getCnpj()) );
	            	} catch (Exception ex) {
	            		parametro.put( "prmCNPJ" , helper.getCNPJ());
	                }
	            } else {
	            	parametro.put( "prmCNPJ" , helper.getCNPJ());
	            }
	            parametro.put( "prmEmployeeName" , helper.getEmployeeName());
	            parametro.put( "prmAdmissionDate" , helper.getAdmissionDate());
	            parametro.put( "prmLastMonthBalance" , helper.getLastMonthBalance());
	            parametro.put( "prmFinalBalance" , helper.getFinalBalance());
	            parametro.put( "prmMonth" , helper.getMonth());
	            parametro.put( "BALANCE_BEAN" , balanceHelperList);
				
				// Generating jasper file if it doesn't exists
	            if ( !new File("reports/bankStatement.jasper").exists() ) {
		            JasperReport relatorio = JasperCompileManager.compileReport( "reports/bankStatement.jrxml" );
		            JRSaver.saveObject(relatorio, "reports/bankStatement.jasper");
	            }

	            // fill the report with data
	            JasperFillManager.fillReportToFile("reports/bankStatement.jasper", parametro, new JRBeanCollectionDataSource( balanceHelperList ));
				// Save PDF in reports folder
				JasperExportManager.exportReportToPdfFile("reports/bankStatement.jrprint");
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

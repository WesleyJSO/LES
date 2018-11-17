package br.com.les.backend.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.MonthlyBalance;
import br.com.les.backend.filter.DashboardFilter;

@Service
@Transactional
public class DashboardFilterDAO extends GenericDAO<DashboardFilter> {

	/**
	 * Select all employee, then select all monthly balances
	 * @return
	 */
	public List<MonthlyBalance> findDashboardData(LocalDate limitMonth) {

		
		
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" from MonthlyBalance m where m.monthAndYear > :limitMonth");
		@SuppressWarnings("unchecked")
		List<MonthlyBalance> monthlyBalanceList = em.
				createQuery(sb.toString())
				.setParameter("limitMonth", limitMonth)
				.getResultList();
		
		/*sb.append(" select m.employee_id, m.month_and_year,"
				+ " sum(m.balance_hours), sum(m.balance_minutes)");
		sb.append(" from monthly_balance m");
		sb.append(" group by m.employee_id, m.month_and_year");
		sb.append(" order by m.employee_id");*/
		
		
		return monthlyBalanceList;
	}

	
}

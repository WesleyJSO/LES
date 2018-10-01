package br.com.les.backend.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.DomainEntity;

@Component
@SuppressWarnings("unchecked")
public class AppointmentDAO extends AbstractDAO {

	private StringBuilder hql;
	private Query query;
	
	public List< DomainEntity > findByDay( Appointment appointment ) {

		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, 0);
		hql = new StringBuilder();
		
		hql.append( "from Appointment a "
				+ "where :date = a.date ");
//				+ "and :employee = a.employee");
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("date", today );
		
//		query.setParameter("employee", appointment.getEmployee() );
		
		return ( List< DomainEntity > ) query.getResultList();
	}

	public <T extends DomainEntity> int softDelete(T entity) {

		hql = new StringBuilder();
		
		hql.append( "update Appointment set active = :active where id = :id");
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("active", false );
		query.setParameter("id", entity.getId() );
		
		return query.executeUpdate();
	}
}

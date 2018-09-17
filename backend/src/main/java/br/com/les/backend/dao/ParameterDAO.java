package br.com.les.backend.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.entity.User;

@Component
@SuppressWarnings("unchecked")
public class ParameterDAO extends AbstractDAO {

	private StringBuilder hql;
	private Query query;
	
	@SuppressWarnings("deprecation")
	public List< DomainEntity > findByDate( Date date ) {

		hql = new StringBuilder();
		
		hql.append( "from Parameter p "
				+ "where :date between p.startDate "
				+ "and p.endDate");
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("date", date.getDate() );
		
		return ( List< DomainEntity > ) query.getResultList();
	}

	
	public List< DomainEntity > findActive ( Parameter parameter ) {
		
		hql = new StringBuilder();
		
		hql.append( "from Parameter p "
				+ "where p.active = :active");
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("active", parameter.getActive() );
		
		return ( List< DomainEntity > ) query.getResultList();
	}
	
}

package br.com.les.backend.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.User;

@Component
@SuppressWarnings("unchecked")
public class FuncionarioDAO extends AbstractDAO {

	private StringBuilder hql;
	private Query query;
	
	public List< DomainEntity > findByLoginAndSenha( User user ) {

		Login l = user.getLogin();
		hql = new StringBuilder();
		
		hql.append( "from Employee f "
				+ "inner join User u on f.id = u.id"
				+ "where u.email = :email "
				+ "and u.login.password = :password");
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("email", user.getEmail() );
		query.setParameter("password", l.getPassword() );
		
		return ( List< DomainEntity > ) query.getResultList();
	}

	
	public List< DomainEntity > dinamicSearch ( Employee funcionario ) {
		
		criteriaQuery = createCriteria();
		
		Root< Employee > funcionarios = criteriaQuery.from( Employee.class );
		
		criteriaQuery.select( funcionarios );
		criteriaQuery.where( 
			criteriaBuilder.and( funcionarios.get( "name" ), booleanParam )
		);
		
		
		Query query = getEntityManager().createQuery( criteriaQuery );
		return ( List< DomainEntity > ) query.getResultList();
	}
	
}

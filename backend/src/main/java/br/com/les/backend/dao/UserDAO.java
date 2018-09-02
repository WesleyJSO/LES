package br.com.les.backend.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.User;

/**
 * 
 * @author José Wesley
 *
 * DAO class to create complex dynamic and strong typed queries using criteria API,
 * To reach that goal, this class have a EntityManager inherited from AbstractDAO to allow the work with Criteria.
 * It's a @Component @Autowired in his respective service class.
 */
@Component
@SuppressWarnings("unchecked")
public class UserDAO extends AbstractDAO {

	private StringBuilder hql;
	private Query query;
	
	public List< DomainEntity > findByEmailAndPassword( User user ) {

		Login l = user.getLogin();
		hql = new StringBuilder();
		
		hql.append( "from User u "
				+ "where u.email = :email "
				+ "and u.login.password = :password" );
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("email", user.getEmail() );
		query.setParameter("password", l.getPassword() );
		
		return ( List< DomainEntity > ) query.getResultList();
	}
}

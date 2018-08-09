package br.com.les.backend.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Usuario;

/**
 * 
 * @author José Wesley
 *
 * DAO class to create complex dynamic and strong typed queries using criteria API,
 * To reach that goal, this class have a EntityManager inherited from AbstractDAO to allow the work with Criteria.
 * It's a @Component @Autowired in his respective service class.
 */
@Component
public class UsuarioDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	public List< EntidadeDominio > dinamicSearch ( Usuario usuario ) {
		
		criteriaQuery = createCriteria();
		
		Root< Usuario > usuarios = criteriaQuery.from( Usuario.class );
		
		criteriaQuery.select( usuarios );
		criteriaQuery.where( 
			criteriaBuilder.and( usuarios.get( "nome" ), booleanParam )
		);
		
		
		Query query = getEntityManager().createQuery( criteriaQuery );
		return ( List< EntidadeDominio > ) query.getResultList();
	}
	
}

package br.com.les.backend.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Funcionario;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.Usuario;

@Component
@SuppressWarnings("unchecked")
public class FuncionarioDAO extends AbstractDAO {

	private StringBuilder hql;
	private Query query;
	
	public List< EntidadeDominio > findByLoginAndSenha( Usuario usuario ) {

		Login l = usuario.getLogin();
		hql = new StringBuilder();
		
		hql.append( "from Funcionario f "
				+ "inner join Usuario u on f.id = u.id"
				+ "where u.login.nomeLogin = :nomeLogin "
				+ "and u.login.senha = :senha");
		
		query = getEntityManager().createQuery( hql.toString() );
		
		query.setParameter("nomeLogin", l.getNomeLogin() );
		query.setParameter("senha", l.getSenha() );
		
		return ( List< EntidadeDominio > ) query.getResultList();
	}

	
	public List< EntidadeDominio > dinamicSearch ( Funcionario funcionario ) {
		
		criteriaQuery = createCriteria();
		
		Root< Funcionario > funcionarios = criteriaQuery.from( Funcionario.class );
		
		criteriaQuery.select( funcionarios );
		criteriaQuery.where( 
			criteriaBuilder.and( funcionarios.get( "nome" ), booleanParam )
		);
		
		
		Query query = getEntityManager().createQuery( criteriaQuery );
		return ( List< EntidadeDominio > ) query.getResultList();
	}
	
}

package br.com.les.backend.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;

/**
 * 
 * @author José Wesley
 * 
 * This class will control low API's configuration in case this necessities occurs.
 */
public abstract class AbstractDAO {

	@PersistenceContext
    private EntityManager entityManager;

	protected CriteriaBuilder criteriaBuilder;
	protected CriteriaQuery< Object > criteriaQuery;
	
	protected ParameterExpression< String > stringParam;
	protected ParameterExpression< Long > longParam;
	protected ParameterExpression< Boolean > booleanParam;
	
	protected CriteriaQuery< Object > createCriteria () {
		
		criteriaBuilder = entityManager.getCriteriaBuilder();
		
		stringParam = criteriaBuilder.parameter( String.class );
		longParam = criteriaBuilder.parameter( Long.class );
		booleanParam = criteriaBuilder.parameter( Boolean.class );
		
		return criteriaBuilder.createQuery();
	}
	
	protected TypedQuery< ? > createQuery( CriteriaQuery< ? > query ) {
		return entityManager.createQuery( query );
	}
	
	protected EntityManager getEntityManager () {
		return this.entityManager;
	}
}

package br.com.les.backend.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.User;
import br.com.les.backend.repository.GenericRepository;
import br.com.les.backend.repository.UserRepository;
@Component
public class GenericDAO<T extends DomainEntity> implements IDAO<T> {
	
	@PersistenceContext    
	protected EntityManager em;

	@Autowired
	protected Map<String, GenericRepository<T>> repositoryMap;
	
	protected GenericRepository<T> repository;

	protected UserRepository userRepository;
	
	private GenericRepository<T> findRepository(T clazz) {
		repositoryMap.forEach((k, v) -> {
			if(k.toLowerCase().contains(clazz.getClass().getSimpleName().toLowerCase()))
				repository = v;
		});
		return repository;
	}
	
	@Override
	public T save(T entity) {
		return findRepository(entity).save(entity);
	}
	
	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@Override
	public List<T> findAll(T entity) {
		return findRepository(entity).findAll();
	}
	
	public List<T> findByActive(T entity) {
		return findRepository(entity).findByActive();
	}

	public List<T> findByInactive(T entity) {
		return findRepository(entity).findByInactive();
	}
	
	public boolean setActiveById(T entity) {
		return findRepository(entity).setActiveById(entity.getId());
	}
	
	public boolean setInactiveById(T entity) {
		return findRepository(entity).setInactiveById(entity.getId());
	}
	
	@Override
	public T findById(Long id, Class<? extends T> clazz) {
		return em.find(clazz, id);
	}
	
	@Override
	public T findByEmailAndPassword(String password, String email, Class<? extends User> clazz) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByParameters(T clazz) {
		
		String sql = "select t from " + clazz.getClass().getSimpleName() + " where ";
	    String atributeName;
	    Object methodValue;
	    
		Method[] methodList = clazz.getClass().getMethods();
		Field[] fieldList = clazz.getClass().getFields();
		
		for(Method method : methodList) {
			if(method.getName().contains("get") || method.getName().contains("is")) {
				try {					
					methodValue = method.invoke(clazz);
					for(Field field : fieldList) {
						atributeName = field.getName();
						
						if(method.getName().contains(atributeName)) {
							if (!(methodValue.equals("") || methodValue.equals(0) || method.toString().contains("Id"))) {

                                if (method.getReturnType().getSimpleName().toLowerCase().equals("string"))
                                    sql = sql + clazz.getClass().getSimpleName().toLowerCase() + "." + atributeName + " like %" + methodValue + "% and ";

                                else if (method.getReturnType().getSimpleName().toLowerCase().equals("long") && methodValue.equals(0))
                                    sql = sql + clazz.getClass().getSimpleName().toLowerCase() + "." + atributeName + " = " + methodValue + " and ";

//                                else if(method.invoke(clazz) instanceof DomainEntity) {
//                                	sql = sql + " join " + method.invoke(clazz).getClass() + "r on t." + method.invoke(clazz).getClass(); 
//                                }
                            } else
                                break;
						}
					}
					sql = sql.substring(sql.length() - 4, sql.length());
					
					return em.createNativeQuery(sql, clazz.getClass()).getResultList();
					
				} catch (IllegalAccessException e) { e.printStackTrace(); 
				} catch (IllegalArgumentException e) { e.printStackTrace();
				} catch (InvocationTargetException e) { e.printStackTrace();
				}
			}
		}
		return null;
	}


}

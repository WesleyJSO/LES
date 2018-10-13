package br.com.les.backend.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class AbstractDomainEntityDAO<T extends DomainEntity> {
	
	@PersistenceContext
	protected EntityManager em;


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

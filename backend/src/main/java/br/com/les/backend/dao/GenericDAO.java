package br.com.les.backend.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.repository.GenericRepository;

@Component
public class GenericDAO<T extends DomainEntity> implements IDAO<T> {
	
	private Map<String, LocalDateTime> dateMap = new HashMap<>();
	private static DateTimeFormatter pettern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	protected Map<String, GenericRepository<T>> repositoryMap;
	
	private GenericRepository<T> getRepository(T clazz) {
		for (Entry<String, GenericRepository<T>> e : repositoryMap.entrySet())
			if(e.getKey()
					.substring(0, clazz.getClass().getSimpleName().length())
					.toLowerCase()
					.equals(clazz.getClass().getSimpleName().toLowerCase()))
				return e.getValue();
		
		return null;
	}
	
	@Override
	public T save(T entity) {
		return getRepository(entity).save(entity);
	}
	
	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@Override
	public List<T> findAll(T entity) {
		return getRepository(entity).findAll();
	}
	
	public List<T> findByActive(T entity) {
		return getRepository(entity).findByActive();
	}

	public List<T> findByInactive(T entity) {
		return getRepository(entity).findByInactive();
	}
	
	public boolean setActiveById(T entity) {
		return getRepository(entity).setActiveById(entity.getId());
	}
	
	public boolean setInactiveById(T entity) {
		return getRepository(entity).setInactiveById(entity.getId());
	}
	
	@Override
	public T findById(Long id, Class<? extends T> clazz) {
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByParameters(T clazz) {
		
		String sql = "select t from " + clazz.getClass().getSimpleName() + " t where 1=1 ";
	    
		List<Method> methodList = getMethodsFromClass(clazz);
		List<Field> fieldList = getFieldsFromClass(clazz);
		Map<Field, Method> attributesMap = MakeMapToMethods(methodList, fieldList);

		for (Entry<Field, Method> item : attributesMap.entrySet()) {
			try {
				if(item.getValue().invoke(clazz) != null && !item.getValue().invoke(clazz).equals("") && !item.getValue().invoke(clazz).equals(0)) {
					sql += queryForString(item.getKey(), item.getValue(), clazz);
					sql += queryForNumber(item.getKey(), item.getValue(), clazz);
					storeDateTypes(item.getKey(), item.getValue(), clazz);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
			
		}
		sql += queryForDate();
		return (List<T>) em.createQuery(sql).getResultList(); 
	}

	private String queryForDate() {
		String sql = "";
		if(dateMap.keySet().size() == 2) {
			long days;
			LocalDateTime firstDate = null;
			String lastKey = null;
			for (Entry<String, LocalDateTime> item : dateMap.entrySet()) {
				if(item.getKey().equals(lastKey) && firstDate != null) {
					days = ChronoUnit.DAYS.between(firstDate, item.getValue());
					if(days > 0) {
						sql += " and t." + lastKey + " between '" + firstDate.format(pettern) + "' and '" + item.getValue().format(pettern) + "'";
					} else {
						sql += " and t." + lastKey + " between '" + item.getValue().format(pettern) + "' and '" + firstDate.format(pettern) + "'";
					}
				} else if(!item.getKey().equals(lastKey) && firstDate != null) {
					days = ChronoUnit.DAYS.between(firstDate, item.getValue());
					if(days > 0) {
						sql += " and t." + item.getKey() + " > " + firstDate.format(pettern);
						sql += " and t." + item.getKey() + " < " + item.getValue().format(pettern);
					} else {
						sql += " and t." + item.getKey() + " > " + item.getValue().format(pettern);
						sql += " and t." + item.getKey() + " < " + firstDate.format(pettern);
					}
				}
				firstDate = item.getValue();
				lastKey = item.getKey();
			}
		} else if(dateMap.keySet().size() == 1) {
			LocalDateTime localDateTime = dateMap.entrySet().iterator().next().getValue();
			sql += " and t." + dateMap.keySet().iterator().next();
			sql += " between '" + localDateTime.toLocalDate().atStartOfDay().format(pettern);
			sql += "' and '" + localDateTime.toLocalDate().atTime(LocalTime.MAX).format(pettern) + "'";

		}
		return sql;
	}

	private void storeDateTypes(Field f, Method m, T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(m.getReturnType() == LocalDateTime.class) {
			dateMap.put(f.getName(), (LocalDateTime) m.invoke(clazz));
		}
	}

	private String queryForNumber(Field f, Method m, T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(m.getReturnType() == Integer.class || m.getReturnType() == Long.class || m.getReturnType() == Float.class
				|| m.getReturnType() == Double.class || m.getReturnType() == Integer.class 
				|| m.getReturnType() == Boolean.class) {
			return " and t." + f.getName() + "=" +  m.invoke(clazz);
		}
		return "";
	}

	private String queryForString(Field f, Method m, T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(m.getReturnType() == String.class) {
			return " and t." + f.getName() + " like '%" + m.invoke(clazz) + "%'";
		}
		return "";
	}

	private Map<Field, Method> MakeMapToMethods(List<Method> methodList, List<Field> fieldList) {
	
		Map<Field, Method> methodMap = new HashMap<>();
		
		for (Method method : methodList)
			if(method.getReturnType() != Void.TYPE && method.getReturnType().isPrimitive()
				|| method.getReturnType() == String.class || method.getReturnType() == Long.class || method.getReturnType() == Double.class
				|| method.getReturnType() == Integer.class || method.getReturnType() == LocalDateTime.class
				|| method.getReturnType() == Boolean.class || method.getReturnType() == Float.class)
				for (Field field : fieldList) 				
					if(field.getName().toLowerCase().equals(method.getName().toLowerCase().substring(3, method.getName().length()))) {
						methodMap.put(field, method);
						break;
					}
		return methodMap;
	}

	private List<Method> getMethodsFromClass(T clazz) {
		
		List<Method> methods = new ArrayList<>();
		Class<?> entity = clazz.getClass();
		
		while (entity != null && entity != Object.class) {
	        Collections.addAll(methods, entity.getDeclaredMethods());
	        entity = entity.getSuperclass();
	    }
		return methods;
	}

	private List<Field> getFieldsFromClass(T clazz) {
		
		List<Field> fields = new ArrayList<>();
		Class<?> entity = clazz.getClass();
		
	    while (entity != null && entity != Object.class) {
	        Collections.addAll(fields, entity.getDeclaredFields());
	        entity = entity.getSuperclass();
	    }
		return fields;
	}
}

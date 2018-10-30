package br.com.les.backend.dao;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
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
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.les.backend.annotation.DateQuery;
import br.com.les.backend.annotation.ListQuery;
import br.com.les.backend.annotation.Query;
import br.com.les.backend.annotation.StringQuery;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.repository.GenericRepository;

@Component
@Service
@Transactional
public class GenericDAO<T extends DomainEntity> implements IDAO<T> {
	
	
	private Map<String, LocalDateTime> dateTimeMap; 	// for dates that will query for days and time of a day
	private Map<String, LocalDate> dateMap; 			// for dates that will query for month
	private static DateTimeFormatter datePettern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter monthYearPettern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
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
	public void delete(T entity) {
		getRepository(entity).delete(entity);
	}
	
	@Override
	public T save(T entity) {
		return getRepository(entity).save(entity);
	}
	
	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String sql = "";
		Query q = clazz.getClass().getAnnotation(Query.class);
		if(q != null)
			sql = "select t from " + q.value() + " t where 1=1 ";
		else 
			sql = "select t from " + clazz.getClass().getSimpleName() + " t where 1=1 ";
			
	    
		dateMap = new HashMap<>();
		dateTimeMap = new HashMap<>();
		List<Method> methodList = getMethodsFromClass(clazz);
		List<Field> fieldList = getFieldsFromClass(clazz);
		Map<Field, Method> attributesMap = MakeMapToMethods(methodList, fieldList);

		for (Entry<Field, Method> item : attributesMap.entrySet()) {
			if(item.getValue().invoke(clazz) != null && !item.getValue().invoke(clazz).equals("") && !item.getValue().invoke(clazz).equals(0)) {
				sql += queryForString(item.getKey(), item.getValue(), clazz);
				sql += queryForNumber(item.getKey(), item.getValue(), clazz);
				sql += queryForList(item.getKey(), item.getValue(), clazz);
				storeDateTimeTypes(item.getKey(), item.getValue(), clazz);
				storeDateTypes(item.getKey(), item.getValue(), clazz);
			}			
		}
		sql += queryForMonth();
		sql += queryForDateTime();
		sql += queryForAnotation(methodList, clazz);
		return (List<T>) em.createQuery(sql).getResultList(); 
	}

	private String queryForDateTime() {
		String sql = "";
		if(dateTimeMap.keySet().size() == 2) {
			long days;
			LocalDateTime firstDate = null;
			String firstKey = null;
			for (Entry<String, LocalDateTime> item : dateTimeMap.entrySet()) {
				if(item.getKey().equals(firstKey) && firstDate != null) { // same date field
					days = ChronoUnit.DAYS.between(firstDate, item.getValue());
					if(days > 0) {
						sql += " and t." + firstKey + " between '" + firstDate.format(datePettern) + "' and '" + item.getValue().format(datePettern) + "'";
					} else {
						sql += " and t." + firstKey + " between '" + item.getValue().format(datePettern) + "' and '" + firstDate.format(datePettern) + "'";
					}
				} else if(!item.getKey().equals(firstKey) && firstDate != null) { // different date field's
					days = ChronoUnit.DAYS.between(firstDate, item.getValue());
					if(days > 0) {
						sql += " and t." + firstKey + " > " + firstDate.format(datePettern);
						sql += " and t." + item.getKey() + " < " + item.getValue().format(datePettern);
					} else {
						sql += " and t." + item.getKey() + " > " + item.getValue().format(datePettern);
						sql += " and t." + firstKey + " < " + firstDate.format(datePettern);
					}
				}
				firstDate = item.getValue();
				firstKey = item.getKey();
			}
		} else if(dateTimeMap.keySet().size() == 1) {
			LocalDateTime localDateTime = dateTimeMap.entrySet().iterator().next().getValue();
			sql += " and t." + dateTimeMap.keySet().iterator().next();
			sql += " between '" + localDateTime.toLocalDate().atStartOfDay().format(datePettern);
			sql += "' and '" + localDateTime.toLocalDate().atTime(LocalTime.MAX).format(datePettern) + "'";

		}
		return sql;
	}

	private String queryForMonth() {
		String sql = "";
		if(dateMap.keySet().size() == 1) {
			Entry<String, LocalDate> entry = dateMap.entrySet().iterator().next();
			sql = " and t." + entry.getKey() 
				+ " between '" + entry.getValue().with(firstDayOfMonth()).format(monthYearPettern) + "'"
				+ " and '" + entry.getValue().with(lastDayOfMonth()).format(monthYearPettern) + "'";
		}
		return sql;
	}
	
	private void storeDateTypes(Field f, Method m, T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(m.getReturnType() == LocalDate.class) {
			dateMap.put(f.getName(), (LocalDate) m.invoke(clazz));
		}
	}
	
	private void storeDateTimeTypes(Field f, Method m, T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if(m.getReturnType() == LocalDateTime.class) {
			dateTimeMap.put(f.getName(), (LocalDateTime) m.invoke(clazz));
		}
	}

	private String queryForList(Field f, Method m, T clazz) {
		String sql = "";
		if(m.getReturnType() == List.class) {
			try {
				@SuppressWarnings("unchecked")
				List<? super T> aList = (List<? super T>) m.invoke(clazz);
				for (Object item : aList)
					sql += " or t." + f.getName() + "=" + item;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) { e.printStackTrace(); }
		}
		return sql;
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

	private String queryForAnotation(List<Method> methodList, T clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String sql = "";
		for (Method method : methodList) {
			if(method.getAnnotation(StringQuery.class) != null) {
				StringQuery s = method.getAnnotation(StringQuery.class);
				sql += s.multipleSelector() + s.name() + s.comparator() + method.invoke(clazz) + s.closeWith();
			} else if(method.getAnnotation(DateQuery.class) != null) {
				DateQuery d = method.getAnnotation(DateQuery.class);
				sql += d.multipleSelector() + d.name() + d.comparator() + method.invoke(clazz) + "'";
			} else if(method.getAnnotation(ListQuery.class) != null) {
				ListQuery l = method.getAnnotation(ListQuery.class);
				@SuppressWarnings("unchecked")
				List<Object> aList =  (List<Object>) method.invoke(clazz);
				for (Object object : aList) {
					sql += l.multipleSelector() + l.name() + l.comparator() + object.toString() + "'";
				}
			}
		}
		return sql;
	}
	
	private Map<Field, Method> MakeMapToMethods(List<Method> methodList, List<Field> fieldList) {
	
		Map<Field, Method> methodMap = new HashMap<>();
		
		for (Method method : methodList)
			if(method.getReturnType() != Void.TYPE && method.getReturnType().isPrimitive()
				|| method.getReturnType() == String.class || method.getReturnType() == Long.class 
				|| method.getReturnType() == Double.class || method.getReturnType() == Integer.class 
				|| method.getReturnType() == Boolean.class || method.getReturnType() == Float.class
				|| method.getReturnType() == LocalDate.class || method.getReturnType() == LocalDateTime.class
				|| method.getReturnType() == LocalTime.class) {
				
				for (Field field : fieldList) {
					if(field.getName().toLowerCase().equals(method.getName().toLowerCase().substring(3, method.getName().length()))) {
						methodMap.put(field, method);
						break;
					}
				}
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

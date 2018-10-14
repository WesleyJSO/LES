package br.com.les.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.DomainEntity;

public abstract class AbstractService<T extends DomainEntity> implements IService<T> {

	@Autowired protected GenericDAO<T> genericDAO;

	@Override
	public T save(T clazz) {
		return genericDAO.save(clazz);
	}

	@Override
	public T update(T clazz) {
		return genericDAO.update(clazz);
	}
	
	@Override
	public List<T> findAll(T entity) {
		return genericDAO.findAll(entity);
	}	

	@Override
	public List<T> findByActive(T entity) {
		return genericDAO.findByActive(entity);
	}
	
	@Override
	public List<T> findByInactive(T entity) {
		return genericDAO.findByInactive(entity);
	}

	@Override
	public boolean setActiveById(T entity) {
		return genericDAO.setActiveById(entity);
	}

	@Override
	public boolean setInactiveById(T entity) {
		return genericDAO.setInactiveById(entity);
	}

	@Override
	public List<T> findByParameters(T entity) {
		return genericDAO.findByParameters(entity);
	}

	@Override
	public T findById(Long id, Class<? extends T> clazz) {
		return genericDAO.findById(id, clazz);
	}

}

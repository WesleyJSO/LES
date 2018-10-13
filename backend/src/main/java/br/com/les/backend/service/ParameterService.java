package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.ParameterDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Parameter;
import br.com.les.backend.repository.ParameterRepository;

@Service
@Transactional
public class ParameterService implements IService<Parameter> {

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private ParameterDAO parameterDAO;
	
	@SuppressWarnings("unchecked")
	public List< Parameter > findAll() {
		return parameterRepository.findAll();
	}

	@Override
	public Parameter save( Parameter entity ) {

		return parameterRepository.save(entity);
	}

	@Override
	public List<Parameter> findByParameters( Parameter entity ) {

//		List< Parameter > resultList = parameterDAO.findActive(entity);
//		if ( resultList.isEmpty() ) {
//			resultList.add(entity);
//		}
		return null;
	}

	@Override
	public int softDelete(Parameter entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}

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
public class ParameterService implements IService {

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private ParameterDAO parameterDAO;
	
	@SuppressWarnings("unchecked")
	public List< Parameter > findAll() {
		return parameterRepository.findAll();
	}

	@Override
	public DomainEntity save( DomainEntity entity ) {

		return parameterRepository.save( ( Parameter ) entity );
	}

	@Override
	public List< DomainEntity > findByParameters( DomainEntity entity ) {

		List< DomainEntity > resultList = parameterDAO.findActive( ( Parameter ) entity );
		if ( resultList.isEmpty() ) {
			resultList.add(entity);
		}
		return resultList;
	}

}

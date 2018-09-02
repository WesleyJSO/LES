package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.EmployeeDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService implements IService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public long count() {
		return employeeRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	public List< Employee > findAll() {
		return employeeRepository.findAll();
	}
	
	public void deleteById( long employeeId ) {
		
		employeeRepository.deleteById( employeeId );
	}

	@Override
	public DomainEntity save( DomainEntity entity ) {

		return employeeRepository.save( ( Employee ) entity );
	}

	@Override
	public List< DomainEntity > findByParameters( DomainEntity entity ) {

		return employeeDAO.findByLoginAndSenha( ( Employee ) entity );
	}

}

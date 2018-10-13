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
public class EmployeeService implements IService<Employee> {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public long count() {
		return employeeRepository.count();
	}
	
	public List< Employee > findAll() {
		return employeeRepository.findAll();
	}
	
	public void deleteById( long employeeId ) {
		employeeRepository.deleteById( employeeId );
	}

	@Override
	public Employee save(Employee entity) {
		return employeeRepository.save(entity );
	}

	@Override
	public List<Employee> findByParameters(Employee entity) {
		return null;
		// return employeeDAO.findByLoginAndSenha(entity);
	}

	@Override
	public int softDelete(Employee entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}

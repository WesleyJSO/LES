package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.FuncionarioDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService implements IService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private FuncionarioDAO employeeDAO;
	
	public long count() {
		return employeeRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	public List< Employee > findAll() {
		return employeeRepository.findAll();
	}
	
	public void deleteById( long idUsuario ) {
		
		employeeRepository.deleteById( idUsuario );
	}

	@Override
	public EntidadeDominio save( EntidadeDominio entidade ) {

		return funcionarioRepository.save( ( Funcionario ) entidade );
	}

	@Override
	public List< EntidadeDominio > findByParameters( EntidadeDominio entidade ) {

		return funcionarioDAO.findByLoginAndSenha( ( Funcionario ) entidade );
	}

}

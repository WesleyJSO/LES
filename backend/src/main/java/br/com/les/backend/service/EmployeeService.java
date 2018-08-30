package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.FuncionarioDAO;
import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Funcionario;
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
	public List< Funcionario > findAll() {
		return employeeRepository.findAll();
	}
	
	public void deleteById( long idUsuario ) {
		
		employeeRepository.deleteById( idUsuario );
	}

	@Override
	public EntidadeDominio save( EntidadeDominio entity ) {

		return employeeRepository.save( ( Funcionario ) entity );
	}

	@Override
	public List< EntidadeDominio > findByParameters( EntidadeDominio entity ) {

		return employeeDAO.findByLoginAndSenha( ( Funcionario ) entity );
	}

}

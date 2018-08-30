package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.FuncionarioDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.repository.FuncionarioRepository;

@Service
@Transactional
public class FuncionarioService implements IService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	public long count() {
		return funcionarioRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	public List< Employee > findAll() {
		return funcionarioRepository.findAll();
	}
	
	public void deleteById( long idUsuario ) {
		
		funcionarioRepository.deleteById( idUsuario );
	}

	@Override
	public DomainEntity save( DomainEntity entidade ) {

		return funcionarioRepository.save( ( Employee ) entidade );
	}

	@Override
	public List< DomainEntity > findByParameters( DomainEntity entidade ) {

		return funcionarioDAO.findByLoginAndSenha( ( Employee ) entidade );
	}

}

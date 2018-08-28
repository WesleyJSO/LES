package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.FuncionarioDAO;
import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Funcionario;
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
	public List< Funcionario > findAll() {
		return funcionarioRepository.findAll();
	}
	
	public void deleteById( long idUsuario ) {
		
		funcionarioRepository.deleteById( idUsuario );
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

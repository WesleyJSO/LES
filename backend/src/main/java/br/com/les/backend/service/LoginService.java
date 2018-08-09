package br.com.les.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.les.backend.dao.LoginDAO;
import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Login;
import br.com.les.backend.repositories.LoginRepository;

@Service
@Transactional
public class LoginService implements IService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
    private LoginDAO loginDAO;

	@Autowired
    private Login login;
	
	@SuppressWarnings("unchecked")
	@Override
	public List< Login > findAll() {
		return loginRepository.findAll();
	}

	@Override
	public Login save( EntidadeDominio entidade ) {
		return loginRepository.save( ( Login ) entidade );
	}
	
	public LoginRepository getLoginRepository() {
	    return this.loginRepository;
    }

	@Override
	public List< EntidadeDominio > findByParameters( EntidadeDominio entidade ) {
		
		login = ( Login ) entidade;
		return loginRepository.findByNomeLoginAndSenha( login.getNomeLogin(), login.getSenha() );
	}
}

package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.UsuarioDAO;
import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Usuario;
import br.com.les.backend.repository.UserRepository;

/**
 * 
 * @author José Wesley
 *
 * The service classes are defined to create a layer between database and business logic,
 * will be responsible to manage the necessaries DAOs and Repositories to handle the business logic and concepts that needs to be implemented.
 * Have the @Transactional annotation to handle the persistence context and transactions with database.
 */
@Service
@Transactional
public class UserService implements IService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public long count() {
		return userRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	public List< Usuario > findAll() {
		return userRepository.findAll();
	}
	
	public void deleteById( long userId ) {
		
		userRepository.deleteById( userId );
	}

	@Override
	public EntidadeDominio save( EntidadeDominio entity ) {

		return userRepository.save( ( Usuario ) entity );
	}

	@Override
	public List< EntidadeDominio > findByParameters( EntidadeDominio entity ) {

		return usuarioDAO.findByLoginAndSenha( ( Usuario ) entity );
	}
}

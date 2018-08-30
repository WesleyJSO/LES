package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.UsuarioDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.User;
import br.com.les.backend.repository.UsuarioRepository;

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
public class UsuarioService implements IService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public long count() {
		return usuarioRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	public List< User > findAll() {
		return usuarioRepository.findAll();
	}
	
	public void deleteById( long idUsuario ) {
		
		usuarioRepository.deleteById( idUsuario );
	}

	@Override
	public DomainEntity save( DomainEntity entidade ) {

		return usuarioRepository.save( ( User ) entidade );
	}

	@Override
	public List< DomainEntity > findByParameters( DomainEntity entidade ) {

		return usuarioDAO.findByLoginAndSenha( ( User ) entidade );
	}
}

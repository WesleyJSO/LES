package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.UserDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.User;
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
	private UserDAO userDAO;
	
	public long count() {
		return userRepository.count();
	}
	
	@SuppressWarnings("unchecked")
	public List< User > findAll() {
		return userRepository.findAll();
	}
	
	public void deleteById( long userId ) {
		
		userRepository.deleteById( userId );
	}

	@Override
	public DomainEntity save( DomainEntity entity ) {

		return userRepository.save( ( User ) entity );
	}

	@Override
	public List< DomainEntity > findByParameters( DomainEntity entity ) {

		return userDAO.findByEmailAndPassword( ( User ) entity );
	}
}

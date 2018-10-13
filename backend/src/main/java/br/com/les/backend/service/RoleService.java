package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.AbstractDomainEntityDAO;
import br.com.les.backend.entity.Role;
import br.com.les.backend.repository.DomainEntityRepository;

@Service
@Transactional
public class RoleService implements IService<Role> {

	@Autowired
	private DomainEntityRepository<Role> roleRepository;
	
	@Autowired
	private AbstractDomainEntityDAO<Role> roleDAO;

	@Override
	public Role save(Role entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findByActive(true);
	}

	@Override
	public List<Role> findByParameters(Role entity) {
		return roleDAO.findByParameters(entity);
	}

	@Override
	public int softDelete(Role entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}

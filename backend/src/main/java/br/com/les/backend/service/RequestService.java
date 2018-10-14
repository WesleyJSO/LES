package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Request;
import br.com.les.backend.repository.RequestRepository;


@Service
@Transactional
public class RequestService implements IService {
	
	@Autowired
	private RequestRepository requestRepository;

	@Override
	public DomainEntity save(DomainEntity entity) {
		return requestRepository.save( ( Request ) entity );
	}

	@Override
	public List<DomainEntity> findByParameters(DomainEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int softDelete(DomainEntity entity) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

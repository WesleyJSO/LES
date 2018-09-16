package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.CompanyDAO;
import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.repository.CompanyRepository;

@Service
@Transactional
public class CompanyService implements IService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Override
	public DomainEntity save(DomainEntity entity) {
		return companyRepository.save( ( Company ) entity );
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll() {
		return (List<T>) companyRepository.findAll();
	}

	@Override
	public List<DomainEntity> findByParameters(DomainEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

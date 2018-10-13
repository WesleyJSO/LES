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
public class CompanyService implements IService<Company> {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CompanyDAO companyDAO;

	@Override
	public List<Company> findAll() {
		return companyRepository.findByActive( true );
	}

	@Override
	public List<Company> findByParameters(Company entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int softDelete(Company entity) {
		return companyDAO.softDelete(entity);
	}

	@Override
	public Company save(Company entity) {
		return companyRepository.save( entity );
	}

}

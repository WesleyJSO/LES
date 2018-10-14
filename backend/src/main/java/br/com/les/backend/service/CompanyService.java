package br.com.les.backend.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.Company;

@Service
@Transactional
public class CompanyService extends AbstractService<Company> {
	
}

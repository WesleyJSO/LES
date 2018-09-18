package br.com.les.backend.strategy.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.repository.CompanyRepository;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class UpdateCompanyStrategy extends AbstractCompanyStrategy {

	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public Result execute(DomainEntity entity, String callerMethod) {

		result = new Result();
		
		Company c = ( Company ) entity;
		switch ( callerMethod ) {
		case "update":
			c.setActive( true );
			List<Company> companyList = companyRepository.findAll();
			
			boolean isInvalid = false;
			for (Company company : companyList) {
				if(!company.getId().equals(c.getId())) {
					if(company.getCnpj().equals(c.getCnpj())) {
						result.setError( "Cnpj já cadastrado!" );
						isInvalid = true;
					}
					if(company.getStateRegistration().equals(c.getStateRegistration())) {
						result.setError( "Inscrição estadual já cadastrada!" );
						isInvalid = true;
					}
				}
				if(isInvalid) break;
				else continue;
			}
		}
		if(result.isSuccess())
			result.setSuccess( Util.SAVE_SUCESSFUL_COMPANY );
		return result;
	}
}

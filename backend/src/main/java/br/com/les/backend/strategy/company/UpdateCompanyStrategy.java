package br.com.les.backend.strategy.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.service.AbstractService;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class UpdateCompanyStrategy implements IApplicationStrategy<Company> {

	@Autowired
	AbstractService<Company> repository;
	
	@Override
	public Result<Company> execute(Company entity, String callerMethod) {

		Result<Company> result = new Result<>();
		
		Company c = ( Company ) entity;
		switch ( callerMethod ) {
		case "Update":
			c.setActive( true );
			List<Company> companyList = repository.findAll(entity);
			
			boolean isInvalid = false;
			for (Company company : companyList) {
				if(!company.getId().equals(c.getId())) {
					if(((Company) company).getCnpj().equals(c.getCnpj())) {
						result.setError( "Cnpj já cadastrado!" );
						isInvalid = true;
					}
					if(((Company) company).getStateRegistration().equals(c.getStateRegistration())) {
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

package br.com.les.backend.strategy.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Company;
import br.com.les.backend.service.GenericService;
import br.com.les.backend.strategy.IApplicationStrategy;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveCompanyStrategy implements IApplicationStrategy<Company> {
	
	@Autowired
	GenericService<Company> companyService;
	
	@Override
	public Result<Company> execute(Company company) {

		Result<Company> result = new Result<>();
		
		List<Company> companyList = companyService.find(company);
		
		boolean isInvalid = false;
		for (Company c : companyList) {
			if(c.getCnpj().equals(company.getCnpj())) {
				if(!company.getActive())
					result.setError( "Cnpj já cadastrado em empresa desativada!" );
				else	
					result.setError( "Cnpj já cadastrado!" );
				isInvalid = true;
			}
			if(c.getStateRegistration().equals(company.getStateRegistration())) {
				if(c.getActive())
					result.setError( "Inscrição estadual já cadastrada!" );
				else
					result.setError( "Inscrição estadual já cadastrada em empresa desativada!" );
				isInvalid = true;
			}
			if(isInvalid) break;
			else continue;
		}
		if(result.isSuccess())
			result.setSuccess( Util.SAVE_SUCESSFUL_COMPANY );
		return result;
	}
}

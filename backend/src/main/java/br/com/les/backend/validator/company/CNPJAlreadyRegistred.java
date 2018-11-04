package br.com.les.backend.validator.company;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Company;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.CompanyRepository;

public class CNPJAlreadyRegistred implements IStrategy<Company> {

    // private static final Logger LOG = LoggerFactory.getLogger(CNPJAlreadyRegistred.class);
    
	@Autowired CompanyRepository companyRepository;
	
	@Override
	public void process(Company aEntity, INavigationCase<Company> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getCnpj())) {
		
			if(aEntity.getId() != null) { // update company
				companyRepository.findById(aEntity.getId()).ifPresent(comp -> {
					if(comp.getCnpj() != null && !comp.getCnpj().equals(aEntity.getCnpj())) {
						if(companyRepository.findAll().stream()
								.filter(c -> !c.getId().equals(aEntity.getId()))
								.filter(c -> c.getCnpj().equals(aEntity.getCnpj()))
								.findAny().orElse(null) != null) {
							aCase.suspendExecution();
							aCase.getResult().setError("Cnpj já cadastrado!");
						}
					}
				});
			} else { // save new company
				if(companyRepository.findAll().stream()
						.filter(c -> c.equals(aEntity.getCnpj()))
						.findAny().orElse(null) != null) {
					aCase.suspendExecution();
					aCase.getResult().setError("Cnpj já cadastrado!");
				}
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Número do CNPJ não definido!");
		return;
	}
}
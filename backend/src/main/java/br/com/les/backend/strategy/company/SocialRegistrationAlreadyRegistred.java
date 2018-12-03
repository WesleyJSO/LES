package br.com.les.backend.strategy.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Company;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.CompanyRepository;
import br.com.les.backend.utils.Util;

@Configuration
public class SocialRegistrationAlreadyRegistred implements IStrategy<Company> {
    
	@Autowired CompanyRepository companyRepository;
	
	@Override
	public void process(Company aEntity, INavigationCase<Company> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getStateRegistration())) {

			if(aEntity.getId() != null) { // update company
				companyRepository.findById(aEntity.getId()).ifPresent(comp -> {
					if(comp.getStateRegistration() != null && !comp.getStateRegistration().equals(aEntity.getStateRegistration())) {
						if(companyRepository.findAll().stream()
								.filter(c -> !c.getId().equals(aEntity.getId()))
								.filter(c -> c.getStateRegistration().equals(aEntity.getStateRegistration()))
								.findAny().orElse(null) != null) {
							aCase.suspendExecution();
							aCase.getResult().setError("Inscrição estadual já cadastrado!");					
						}
					}
				});
			} else { // save new company
				if(companyRepository.findAll().stream()
						.filter(c -> c.getStateRegistration().equals(aEntity.getStateRegistration()))
						.findAny().orElse(null) != null) {
					aCase.suspendExecution();
					aCase.getResult().setError("Inscrição estadual já cadastrada!");
				}
			}
			aCase.getResult().setSuccess(Util.SAVE_SUCESSFUL_COMPANY);
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Número do inscrição estadual não definido!");
		return;
	}
}
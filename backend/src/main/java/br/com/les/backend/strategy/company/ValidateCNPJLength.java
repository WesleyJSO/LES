package br.com.les.backend.strategy.company;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Strings;

import br.com.les.backend.entity.Company;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class ValidateCNPJLength implements IStrategy<Company> {
	
	private final static Short CNPJ_LENGTH = 14;
	
	@Override
	public void process(Company aEntity, INavigationCase<Company> aCase) {

		if (aEntity != null && !Strings.isNullOrEmpty(aEntity.getCnpj())) {
		
			if(aEntity.getCnpj().length() > CNPJ_LENGTH) { 
				aCase.suspendExecution();
				aCase.getResult().setError("Numero do CNPJ maior que o limite de ".concat(CNPJ_LENGTH.toString()).concat(" caracteres!"));
			} else if(aEntity.getCnpj().length() < CNPJ_LENGTH) {
				aCase.suspendExecution();
				aCase.getResult().setError("Numero do CNPJ menor que o limite de ".concat(CNPJ_LENGTH.toString()).concat(" caracteres!"));				
			}
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("Número do CNPJ não definido!");
		return;
	}
}
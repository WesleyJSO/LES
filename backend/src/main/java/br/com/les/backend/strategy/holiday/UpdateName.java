package br.com.les.backend.strategy.holiday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.Holiday;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;

@Configuration
public class UpdateName implements IStrategy<Holiday> {
	
	@Autowired GenericDAO<Holiday> holidayDao;

	@Override
	public void process(Holiday aEntity, INavigationCase<Holiday> aCase) {

		if (aEntity != null) {
			
			String name = aEntity.getName();
			
			Holiday holiday = holidayDao.find(aEntity).get(0);
		    
			holiday.setName(name);
			return;
		}
		aCase.suspendExecution();
		aCase.getResult().setError("O sistema não pôde salvar o feriado!");
		return;
	}
}

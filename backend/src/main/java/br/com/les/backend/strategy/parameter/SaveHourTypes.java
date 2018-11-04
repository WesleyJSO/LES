package br.com.les.backend.strategy.parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.les.backend.entity.Parameter;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.IStrategy;
import br.com.les.backend.repository.HourTypeRepository;

@Configuration
public class SaveHourTypes implements IStrategy<Parameter> {

	@Autowired HourTypeRepository hourTypeRepository;
	
	@Override
	public void process(Parameter aEntity, INavigationCase<Parameter> aCase) {

		hourTypeRepository.save(aEntity.getOverTime());
		hourTypeRepository.save(aEntity.getCompTime());
		hourTypeRepository.save(aEntity.getBothTypes());
		
	}
	
}

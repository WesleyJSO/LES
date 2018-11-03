package br.com.les.backend.navigator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class Navigator<E extends DomainEntity> implements INavigator<E> {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NavigatorContext context;

	@Autowired
	private Map<String, Navigation<E>> listNavigations = new HashMap<String, Navigation<E>>();
	
	@PostConstruct
	public void init() {
		logger.info("==============================NAVIGATIONS==============================");
		listNavigations.keySet().forEach(logger::info);
		logger.info("=======================================================================");
	}
	
	@Override
	public void run(E aEntity, INavigationCase<E> aCase) {

		aCase.setContext(context);
		navigate(aEntity, aCase);
	}

	private void navigate(E aEntity, INavigationCase<E> aCase) {

		if (aEntity != null) {

			Navigation<E> navigation = listNavigations.get(aCase.getName());

			if (navigation != null && !aCase.isSuspendExecution()) {

				List<IStrategy<E>> activities = navigation.getActivities();

				for (IStrategy<E> strategy : activities) {

					strategy.process(aEntity, aCase);
					if (aCase.isSuspendExecution())
						break;
				}
				
			} else if (!aCase.getName().equals(BusinessCase.DEFAULT_CONTEXT_NAME)) {

				aCase.suspendExecution();
				aCase.getResult().setError(aCase.getName() + " - Não foi encontrada!");
				return;
			} else {
				aCase.getResult().setError("Navigation não encontrado!");
			}
		} else {			
			aCase.suspendExecution();
			aCase.getResult().setError("Entidade nula!");
		}
		
	}
}

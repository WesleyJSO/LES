package br.com.les.backend.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.dao.GenericDAO;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.navigator.INavigationCase;
import br.com.les.backend.navigator.INavigator;
import br.com.les.backend.utils.Result;

@Component
public class ApplicationFacade<T extends DomainEntity> implements IApplicationFacade<T> {

	@Autowired private GenericDAO<T> genericDAO;
	
	@Autowired private INavigator<T> navigator;
	
	@Override
	public Result<T> save(T aEntity, INavigationCase<T> aCase) {
		
		navigator.run(aEntity, aCase);
		if(aCase.getResult().isSuccess() && !aCase.isSuspendExecution()) {
			T saved = genericDAO.save(aEntity);
			aCase.getResult().addResult(saved);
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> update(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if(aCase.getResult().isSuccess() && !aCase.isSuspendExecution()) {
			T updated = genericDAO.update(aEntity);
			aCase.getResult().addResult(updated);
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> delete(T aEntity, INavigationCase<T> aCase) {

		navigator.run(aEntity, aCase);
		if(aCase.getResult().isSuccess() && !aCase.isSuspendExecution()) {
			T deleted = genericDAO.delete(aEntity);
			aCase.getResult().addResult(deleted);
		}
		return aCase.getResult();
	}

	@Override
	public Result<T> find(T aEntity, INavigationCase<T> aCase) {
		
		navigator.run(aEntity, aCase);
		if(aCase.getResult().isSuccess() && !aCase.isSuspendExecution()) {
			List<T> found = genericDAO.find(aEntity);
			aCase.getResult().setResultList(found);
		}
		return aCase.getResult();
	}
}

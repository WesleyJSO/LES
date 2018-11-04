package br.com.les.backend.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.facade.IApplicationFacade;

@Component
public abstract class AbstractCommand<T extends DomainEntity> implements ICommand<T> {

	@Autowired protected IApplicationFacade<T> applicationFacade;
}

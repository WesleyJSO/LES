package br.com.les.backend.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.les.backend.facade.IFacade;

@Component
public abstract class AbstractCommand implements ICommand {

	@Autowired
	protected IFacade facade;
}

package br.com.les.backend.facade;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.service.IService;
import br.com.les.backend.strategy.IStrategy;

/**
 *
 *  @author  José Wesley
 *
 *  this class contains the lists of strategies for each controller,
 *  they will be exposed for dinamic search in the methods of IFacade
 */
@Component
public  abstract class AbstractFacade implements IFacade {

	@Autowired
	protected List< IService > listServices;
	
    @Autowired
    protected Map< String, IStrategy > mapStrategies;

    protected abstract void validar(EntidadeDominio entity, String action, String callerMethod );

}

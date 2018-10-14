package br.com.les.backend.facade;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.service.AbstractService;
import br.com.les.backend.strategy.IStrategy;

public abstract class AbstractFacade<T extends DomainEntity> implements IFacade<T> {

	

}

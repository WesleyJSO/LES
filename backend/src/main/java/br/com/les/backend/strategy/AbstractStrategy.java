package br.com.les.backend.strategy;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.les.backend.entity.DomainEntity;

public abstract class AbstractStrategy<T extends DomainEntity> implements IApplicationStrategy<T> {
	
	protected DomainEntity authenticatedUser () {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		@SuppressWarnings("unchecked")
		List <DomainEntity>list = (List<DomainEntity>) authentication.getPrincipal();
		DomainEntity entity = (DomainEntity) list.get(0);
		
		return entity;
		
	}
}

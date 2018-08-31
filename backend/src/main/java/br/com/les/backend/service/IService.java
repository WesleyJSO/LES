package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.DomainEntity;

public interface IService {

	EntidadeDominio save( EntidadeDominio entidade );
	
	< T > List< T > findAll();

	List< EntidadeDominio > findByParameters(EntidadeDominio entidade);
}

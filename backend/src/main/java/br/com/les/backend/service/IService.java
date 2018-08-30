package br.com.les.backend.service;

import java.util.List;

import br.com.les.backend.entity.EntidadeDominio;

public interface IService {

	EntidadeDominio save( EntidadeDominio employee );
	
	< T > List< T > findAll();

	List< EntidadeDominio > findByParameters(EntidadeDominio employee);
}

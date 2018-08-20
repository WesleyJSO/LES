package br.com.les.backend.facade;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.service.IService;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Resultado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Fachada extends AbstractFachada {

    @Autowired
    private Resultado resultado;
    
    private IService service;

    @Override
    protected void validar( EntidadeDominio entidade, String action ) {

    	String nomeEntidade = entidade.getClass().getSimpleName();
    	
    	for ( IService s : listServices )
			if ( s.getClass().getName().contains( nomeEntidade ) ) {
				service = s;
				break;
			}
		
    	resultado = mapStrategies.get( nomeEntidade ).execute( entidade, action );
    }

    @Override
    public Resultado save(EntidadeDominio entidade) {

    	validar( entidade, Actions.SAVE.getValue() );
    	if ( resultado.isSucesso() )
    		resultado.getListaResultado().add( service.save( entidade ) );
    	return resultado;
    }

    @Override
    public Resultado update(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public Resultado delete( EntidadeDominio entidade ) {

    	return null;
    }

    @Override
    public Resultado findAll( EntidadeDominio entidade ) {
    		
    	validar( entidade, Actions.SEARCH.getValue() );
    	resultado.setListaResultado( service.findAll() );
    	return resultado;    	
    }

    @Override
    public Resultado findByEntidadeDominio( EntidadeDominio entidade ) {
        
    	validar( entidade, Actions.SEARCH.getValue() );
    	resultado.getListaResultado().addAll( service.findByParameters( entidade ) );
    	return resultado;
    }
}

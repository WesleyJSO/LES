package br.com.les.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class Resultado {

    private boolean sucesso;
    private List< String > mensagem = new ArrayList<>();
    
    private List< DomainEntity > listaResultado = new ArrayList<>();
    
    public Resultado() {
    	sucesso = true;
    }

    public void setSucesso( String mensagem ) {
    	
        if ( mensagem != null && !mensagem.equals("") ) {
        	
        	if ( !sucesso )
        		this.mensagem = new ArrayList<>();
	        
        	sucesso = true;
	        this.mensagem.add( mensagem );
        }
    }

    public void setErro( String mensagem ) {
    	
        if ( mensagem != null && !mensagem.equals("") ) {
        	
        	if ( sucesso )
        		this.mensagem = new ArrayList<>();
	    	
        	sucesso = false;
	        this.mensagem.add( mensagem );
        }
    }

    public boolean isSucesso() {
       return sucesso;
    }

    public void setListaResultado( List<DomainEntity> listaResultado ) {
        this.listaResultado = listaResultado;
    }

    public List<DomainEntity> getListaResultado() {
        return this.listaResultado;
    }
    
    public List< String > getMensagem() {
    	
    	if ( mensagem.size() > 0 ) {
	    	return mensagem;
    	} else 
    		return null;
    }
}
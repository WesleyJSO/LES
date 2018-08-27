package br.com.les.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;

@Component
public class Resultado {

    private boolean sucesso;
    private List< String > mensagem = new ArrayList<>();
    
    private List< EntidadeDominio > listaResultado = new ArrayList<>();
    
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

    public void setListaResultado( List<EntidadeDominio> listaResultado ) {
        this.listaResultado = listaResultado;
    }

    public List<EntidadeDominio> getListaResultado() {
        return this.listaResultado;
    }
    
    public List< String > getMensagem() {
    	
    	if ( mensagem.size() > 0 ) {
	    	return mensagem;
    	} else 
    		return null;
    }
}
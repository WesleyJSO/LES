package br.com.les.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;

@Component
public class Result {

    private boolean success;
    private List< String > message = new ArrayList<>();
    
    private List< EntidadeDominio > resultList = new ArrayList<>();
    
    public Result() {
    	success = true;
    }

    public void setSucesso( String message ) {
    	
        if ( message != null && !message.equals("") ) {
        	
        	if ( !success )
        		this.message = new ArrayList<>();
	        
        	success = true;
	        this.message.add( message );
        }
    }

    public void setErro( String message ) {
    	
        if ( message != null && !message.equals("") ) {
        	
        	if ( success )
        		this.message = new ArrayList<>();
	    	
        	success = false;
	        this.message.add( message );
        }
    }

    public boolean isSucesso() {
       return success;
    }

    public void setListaResultado( List<EntidadeDominio> resultList ) {
        this.resultList = resultList;
    }

    public List<EntidadeDominio> getListaResultado() {
        return this.resultList;
    }
    
    public List< String > getMensagem() {
    	
    	if ( message.size() > 0 ) {
	    	return message;
    	} else 
    		return null;
    }
}
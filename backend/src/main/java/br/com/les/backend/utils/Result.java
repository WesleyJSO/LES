package br.com.les.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;

@Component
public class Result {

    private boolean success;
    private List< String > message = new ArrayList<>();
    
    private List< DomainEntity > resultList = new ArrayList<>();
    
    public Result() {
    	success = true;
    }

    public void setSuccess( String message ) {
    	
        if ( message != null && !message.equals("") ) {
        	
        	if ( !success )
        		this.message = new ArrayList<>();
	        
        	success = true;
	        this.message.add( message );
        }
    }

    public void setError( String message ) {
    	
        if ( message != null && !message.equals("") ) {
        	
        	if ( success )
        		this.message = new ArrayList<>();
	    	
        	success = false;
	        this.message.add( message );
        }
    }

    public boolean isSuccess() {
       return success;
    }

    public void setResultList( List<DomainEntity> resultList ) {
        this.resultList = resultList;
    }

    public List<DomainEntity> getResultList() {
        return this.resultList;
    }
    
    public List< String > getMessage() {
    	
    	if ( message.size() > 0 ) {
	    	return message;
    	} else 
    		return null;
    }
}
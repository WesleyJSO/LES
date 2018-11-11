package br.com.les.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import br.com.les.backend.entity.DomainEntity;

@Component
public class Result<T extends DomainEntity> {

    private boolean success;
    private List< String > message = new ArrayList<>();
    
    private List<T> resultList = new ArrayList<>();
    
    public Result() {
    	success = true;
    	message.add("Ação realizada com sucesso!");
    }

    public void setSuccess( String message ) {
    	
        if ( Strings.isNullOrEmpty(message)) {
        	
        	if ( !success )
        		this.message = new ArrayList<>();
	        
        	success = true;
	        this.message.add( message );
        }
    }

    public void setError( String message ) {

        if ( !Strings.isNullOrEmpty(message)) {
        	
        	if ( success )
        		this.message = new ArrayList<>();
	    	
        	success = false;
	        this.message.add( message );
        }
    }

    public boolean isSuccess() {
       return success;
    }
    
    public void setResultList( List<T> resultList ) {
        this.resultList = resultList;
    }

    public List<T> getResultList() {
        return this.resultList;
    }
    
    public List< String > getMessage() {
    	
    	if ( message.size() > 0 ) {
	    	return message;
    	} else 
    		return null;
    }

	public void addResult(T entity) {
		this.resultList.add(entity);
	}
}
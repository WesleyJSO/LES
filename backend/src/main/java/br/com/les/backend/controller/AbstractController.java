package br.com.les.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.les.backend.command.ICommand;
import br.com.les.backend.utils.Result;

@Component
public  abstract class AbstractController {

    @Autowired
    List < ICommand > command;
   
    Result result;
    
    protected String getMethodName( Object o ) {
		return o.getClass().getEnclosingMethod().getName();
	}
    
    protected ICommand run ( String action) {
    	for (ICommand iCommand : command) {
			if ( iCommand.getClass().getName().contains( action ) )
				return iCommand;
		}
    	return null;
    }
}

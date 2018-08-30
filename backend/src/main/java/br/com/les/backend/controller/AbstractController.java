package br.com.les.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.facade.IFacade;
import br.com.les.backend.utils.Result;

@Component
public  abstract class AbstractController {

    @Autowired
    IFacade fachada;
    
    @Autowired
    Result result;
    
    protected String getMethodName( Object o ) {
		return o.getClass().getEnclosingMethod().getName();
	}
}

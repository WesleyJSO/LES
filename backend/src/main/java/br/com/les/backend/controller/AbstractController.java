package br.com.les.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.facade.IFachada;
import br.com.les.backend.utils.Resultado;

@Component
public  abstract class AbstractController {

    @Autowired
    IFachada fachada;
    
    @Autowired
    Resultado resultado;
    
    protected String getMethodName( Object o ) {
		return o.getClass().getEnclosingMethod().getName();
	}
}

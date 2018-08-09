package br.com.les.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.les.backend.Utils.Resultado;
import br.com.les.backend.facade.IFachada;

@Component
public  abstract class AbstractController {

    @Autowired
    IFachada fachada;
    
    @Autowired
    Resultado resultado;
}

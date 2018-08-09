package br.com.les.backend.Utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;

@Component
public class Resultado {

    private boolean sucesso;
    private StringBuilder mensagem = new StringBuilder();
    
    private List< EntidadeDominio > listaResultado = new ArrayList<>();

    public void  setSucesso( String mensagem ) {
        sucesso = true;
        this.mensagem.append( mensagem );
    }

    public void  setErro( String mensagem ) {
        sucesso = false;
        this.mensagem.append(mensagem );
    }

    public boolean  isSucesso() {
       return sucesso;
    }

    public void setListaResultado( List<EntidadeDominio> listaResultado ) {
        this.listaResultado = listaResultado;
    }

    public List<EntidadeDominio> getListaResultado() {
        return this.listaResultado;
    }
    
    public String[] getMensagem() {
    	String[] mensagens = mensagem.toString().split( "[/]" );
    	return mensagens;
    }
}


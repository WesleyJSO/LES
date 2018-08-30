package br.com.les.backend.strategy.user;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.utils.Result;

public interface IUserStrategy {

	Result execute( EntidadeDominio entity, String callerMethod );
}

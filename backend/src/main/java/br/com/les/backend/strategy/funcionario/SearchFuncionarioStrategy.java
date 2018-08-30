package br.com.les.backend.strategy.funcionario;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Login;
import br.com.les.backend.entity.User;
import br.com.les.backend.utils.Resultado;

@Component
public class SearchFuncionarioStrategy extends AbstractFuncionarioStrategy {

	@Override
	public Resultado execute( DomainEntity entidade, String callerMethod ) {
		
		resultado = new Resultado();
		
		User u = ( User ) entidade;
		
		if ( u != null ) {

			Login l = u.getLogin();
			
			switch ( callerMethod ) {
			
				case "findAll":
					
				break;
				
				case "findByUsuario": // search for list of users
					
					if ( l != null && l.getNomeLogin() == null && l.getDataCriacao() != null && u.getEmail() == null && u.getNome() == null )
						resultado.setErro( "Ao menos um dos campos deve ser preenchido!");
					break;
					
				case "findByLogin": // search for logged user
					
					if ( l != null ) {
						if ( l.getNomeLogin() == null || l.getNomeLogin().equals("") )
							resultado.setErro( "Login deve ser preenchido!" );
							
						if ( l.getSenha() == null || l.getSenha().equals("") )
							resultado.setErro( "Senha deve ser preenchida!" );
					
					}
					break;
					
				default:
		        break;
			}
		}
		return resultado;
	}
}

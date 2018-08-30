package br.com.les.backend.strategy.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Funcionario;
import br.com.les.backend.entity.Telefone;
import br.com.les.backend.utils.Result;
import br.com.les.backend.utils.Util;

@Component
public class SaveEmployeeStrategy extends AbstractEmployeeStrategy {

	@Override
	public Result execute(EntidadeDominio entity, String callerMethod ) {
		
		result = new Result();
		
		switch ( callerMethod ) {
		
		case "save":
			
			Funcionario employee = ( Funcionario ) entity;
			
			if( employee.getNome() == null || employee.getNome().isEmpty() )
				result.setErro( Util.ERROR_NAME );
			
			if( employee.getUltimoNome() == null || employee.getUltimoNome().isEmpty() )
				result.setErro( Util.ERROR_LAST_NAME );
			
			if( employee.getEmail() == null || employee.getEmail().isEmpty() )
				result.setErro( Util.ERROR_EMAIL );
			
			/*if( funcionario.getListaRole().isEmpty() )
				resultado.setErro( "Ao menos uma role deve ser selecionada!" ); 
			*/
			if( employee.getListaBaseCalculoHoras().get(0).getSalario() == 0 )
				result.setErro(Util.ERROR_SALARY );
			
			if( employee.getPis() == null || employee.getPis().isEmpty() )
				result.setErro( Util.ERROR_PIS );
			else
				if( employee.getPis().length() < 11 )
					result.setErro( Util.INVALID_PIS);
				
			
			if( employee.getListaBaseCalculoHoras().get(0).getCargaHoraria() == 0 )
				result.setErro( Util.ERROR_WORKLOAD );
			else
				if( employee.getListaBaseCalculoHoras().get(0).getCargaHoraria() >= 10)
					result.setErro( Util.INVALID_WORKLOAD );
			
			if( employee.getGestor().getNome().equals("") )
				result.setErro( Util.ERROR_MANAGER_NAME );
			
			if( employee.getListaTelefone().isEmpty() )
				result.setErro( Util.ERROR_PHONE );
			else {
				List< Telefone > listaRemover = new ArrayList<>();
				for( Telefone telefone : employee.getListaTelefone() ) {
					telefone.setUsuario(employee);
					if( telefone.getNumero().length() == 0 ) {
						listaRemover.add(telefone);
					}
				}
				
				for( Telefone telefone : listaRemover )
					employee.getListaTelefone().remove(telefone);

				
				for( Telefone telefone : employee.getListaTelefone() )
					if( telefone.getNumero().length() < 8 ) {
						result.setErro( Util.INVALID_PHONE.concat( telefone.toString() ) );
						break;
					}
				
			}
			
			if( employee.getLogin() == null || employee.getLogin().getNomeLogin().isEmpty() )
				result.setErro( Util.ERROR_LOGIN );
			
			if( employee.getDataNascimento() == null || employee.getDataNascimento().toString().isEmpty() )
				result.setErro( Util.ERROR_BIRTHDATE );
			else {
				Date dataAtual = new Date();
				
				Long milliseconds = dataAtual.getTime() - employee.getDataNascimento().getTime();
				
				if( milliseconds < 441504000000l ) {
					result.setErro( Util.INVALID_BIRTHDATE );
				}
			}
			
			if( employee.getDataIngressoEmpresa() == null || employee.getDataIngressoEmpresa().toString().isEmpty() ) {
					result.setErro( Util.ERROR_JOINING_DATE );
			}
			
			if( employee.getLogin().getSenha() == null || employee.getLogin().getSenha().isEmpty() )
				result.setErro( Util.INVALID_PASSWORD );
			
			if( result.isSucesso() )
				result.setSucesso( Util.SAVE_SUCCESSFUL_EMPLOYEE );
			
			break;
		}
		
		return result;

	}
}

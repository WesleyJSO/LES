package br.com.les.backend.strategy.funcionario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.les.backend.entity.EntidadeDominio;
import br.com.les.backend.entity.Funcionario;
import br.com.les.backend.entity.Telefone;
import br.com.les.backend.utils.Resultado;

@Component
public class SaveFuncionarioStrategy extends AbstractFuncionarioStrategy {

	@Override
	public Resultado execute(EntidadeDominio entidade, String callerMethod ) {
		
		resultado = new Resultado();
		
		switch ( callerMethod ) {
		
		case "save":
			
			Funcionario funcionario = ( Funcionario ) entidade;
			
			if( funcionario.getNome() == null || funcionario.getNome().isEmpty() )
				resultado.setErro( "Nome deve ser preenchido!" );
			
			if( funcionario.getUltimoNome() == null || funcionario.getUltimoNome().isEmpty() )
				resultado.setErro( "Ultimo nome deve ser preenchido!" );
			
			if( funcionario.getEmail() == null || funcionario.getEmail().isEmpty() )
				resultado.setErro( "E-mail deve ser preenchido!" );
			
			/*if( funcionario.getListaRole().isEmpty() )
				resultado.setErro( "Ao menos uma role deve ser selecionada!" ); 
			*/
			if( funcionario.getBaseCalculoHoras().getSalario() == BigDecimal.ZERO )
				resultado.setErro("O salário deve ser preenchido!");
			
			if( funcionario.getPis() == null || funcionario.getPis().isEmpty() )
				resultado.setErro("O numero PIS deve ser informado!");
			else
				if( funcionario.getPis().length() < 11 )
					resultado.setErro("Numero PIS inválido!");
				
			
			if( funcionario.getBaseCalculoHoras().getCargaHoraria() == 0 )
				resultado.setErro("A carga horária deve ser preenchida!");
			else
				if( funcionario.getBaseCalculoHoras().getCargaHoraria() >= 10)
					resultado.setErro("Carga horária inválida!");
			
			if( funcionario.getGestor().getNome().equals("") )
				resultado.setErro("Gestor deve ser selecionado!");
			
			if( funcionario.getListaTelefone().isEmpty() )
				resultado.setErro( "Pelo menos um telefone deve ser cadastrado!" );
			else {
				List< Telefone > listaRemover = new ArrayList<>();
				for( Telefone telefone : funcionario.getListaTelefone() ) {
					telefone.setUsuario(funcionario);
					if( telefone.getNumero().length() == 0 ) {
						listaRemover.add(telefone);
					}
				}
				
				for( Telefone telefone : listaRemover )
					funcionario.getListaTelefone().remove(telefone);

				
				for( Telefone telefone : funcionario.getListaTelefone() )
					if( telefone.getNumero().length() < 8 ) {
						resultado.setErro("Telefone inválido!");
						break;
					}
				
			}
			
			if( funcionario.getLogin() == null || funcionario.getLogin().getNomeLogin().isEmpty() )
				resultado.setErro( "Login deve ser definido!" );
			
			if( funcionario.getDataNascimento() == null || funcionario.getDataNascimento().toString().isEmpty() )
				resultado.setErro("Data de nascimento deve ser preenchida!");
			else {
				Date dataAtual = new Date();
				
				Long milliseconds = dataAtual.getTime() - funcionario.getDataNascimento().getTime();
				
				if( milliseconds < 441504000000l ) {
					resultado.setErro("Idade mínima para registro: 14 anos!");
				}
			}
			
			if( funcionario.getDataIngressoEmpresa() == null || funcionario.getDataIngressoEmpresa().toString().isEmpty() )
				resultado.setErro("Data de ingresso deve ser preenchida!");
			
			if( funcionario.getLogin().getSenha() == null || funcionario.getLogin().getSenha().isEmpty() )
				resultado.setErro( "Senha inválida!" );
			
			if( resultado.isSucesso() )
				resultado.setSucesso( "Funcionário salvo com sucesso!" );
			
			break;
		}
		
		return resultado;

	}
}

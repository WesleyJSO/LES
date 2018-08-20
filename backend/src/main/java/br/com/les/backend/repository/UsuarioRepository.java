package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Funcionario;

/**
 * 
 * @author José Wesley
 *
 * Repository that extends from JpaRepository, It's store query methods,
 * and in more complex cases, queries in this class will use methods annotated with @Query.
 */
public interface UsuarioRepository extends JpaRepository< Funcionario, Long > { 
	
	Funcionario findByNome( String nome );
}


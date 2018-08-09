package br.com.les.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Usuario;

/**
 * 
 * @author José Wesley
 *
 * Repository that extends from JpaRepository, It's store query methods,
 * and in more complex cases, queries in this class will use methods annotated with @Query.
 */
public interface UsuarioRepository extends JpaRepository< Usuario, Long > { 
	
	Usuario findByNome( String nome );
}


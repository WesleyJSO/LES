package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.User;

/**
 * 
 * @author José Wesley
 *
 * Repository that extends from JpaRepository, It's store query methods,'
 * and in more complex cases, queries in this class will use methods annotated with @Query.
 */
public interface UserRepository extends JpaRepository< User, Long > {
	
}
package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.User;

public interface UserRepository extends GenericRepository<User> {

	<T> T findByEmailAndPassword(String email, String password);
	<T> T findByEmail(String email);

}


package br.com.les.backend.repository;

import br.com.les.backend.entity.User;

public interface UserRepository extends GenericRepository<User> {

	<T> T findByEmailAndPassword(String email, String password);

}


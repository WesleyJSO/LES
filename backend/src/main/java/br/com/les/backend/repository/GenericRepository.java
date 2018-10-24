package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.DomainEntity;

public interface GenericRepository<T extends DomainEntity> extends JpaRepository <T, Long> {
	
	@Query("select t from #{#entityName} t where t.active = true")
	List<T> findByActive();
	
	@Query("select t from #{#entityName} t where t.active = false")
	List<T> findByInactive();

	@Query("select t from #{#entityName} t where t.active = true and id = ?2")
	T findActiveById(Long id);
	
	@Query("update #{#entityName} t set t.active = true where id = ?2")
	boolean setActiveById(Long id);

	@Query("update #{#entityName} t set t.active = false where id = ?2")
	boolean setInactiveById(Long id);
	
}

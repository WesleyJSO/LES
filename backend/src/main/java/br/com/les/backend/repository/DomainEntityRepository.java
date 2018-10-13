package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.DomainEntity;

public interface DomainEntityRepository<T extends DomainEntity> extends JpaRepository <T, Long >{
	
	@Query("select d from #{#entityName} d where ?1 in d.active")
	List<T> findByActive(boolean actice);
}

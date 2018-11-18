package br.com.les.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.BankedHours;

public interface BankedHoursRepository extends GenericRepository<BankedHours>{


	@Query("from #{#entityName} t where t.active = true and t.employee.id = ?1")
	Optional<BankedHours> findByEmployeeId(Long employeeId);
}

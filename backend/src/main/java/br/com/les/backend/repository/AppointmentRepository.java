package br.com.les.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.Appointment;

public interface AppointmentRepository extends GenericRepository<Appointment>{

	@Query("select t from Appointment t inner join t.histChangeMonthlyBalanceList where t.date > ?1")
	List< Appointment > findByDate(LocalDateTime date);
}

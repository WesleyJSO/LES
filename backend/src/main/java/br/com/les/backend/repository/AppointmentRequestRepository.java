package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.AppointmentRequest;

public interface AppointmentRequestRepository extends GenericRepository<AppointmentRequest> {

	@Query("from #{#entityName} t where t.appointment.id = ?1")
	List<AppointmentRequest> findByAppointmentId(Long id);
	
	@Query("from #{#entityName} t where t.appointment.id = ?1 and t.status = ?2")
	List<AppointmentRequest> findEvaluatedByAppointmentId(Long id, Integer status);
}

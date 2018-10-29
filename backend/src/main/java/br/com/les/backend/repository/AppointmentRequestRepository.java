package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.AppointmentRequest;

public interface AppointmentRequestRepository extends GenericRepository<AppointmentRequest> {

	@Query("select t from #{#entityName} t where t.appointment.id = ?1")
	List<AppointmentRequest> findByIdAppointment(Long id);
}
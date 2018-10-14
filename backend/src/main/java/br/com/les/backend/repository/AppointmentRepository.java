package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.DomainEntity;

public interface AppointmentRepository extends JpaRepository< Appointment, Long > {
	
	List<DomainEntity> findByDate( String today );

}

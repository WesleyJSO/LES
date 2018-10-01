package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Appointment;

public interface AppointmentRepository extends JpaRepository< Appointment, Long > {

}

package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository< Employee, Long > {

}

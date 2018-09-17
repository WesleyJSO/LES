package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Employee;
import br.com.les.backend.entity.Parameter;

public interface ParameterRepository extends JpaRepository< Parameter, Long > {

}

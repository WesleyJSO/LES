package br.com.les.backend.repository;


import br.com.les.backend.entity.Employee;

public interface EmployeeRepository extends GenericRepository<Employee>{

	Employee findByUserEmail(String email);
	
}

package br.com.les.backend.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.les.backend.entity.Employee;

@Service
@Transactional
public class EmployeeService extends AbstractService<Employee> {

}

package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Company;

public interface CompanyRepository extends JpaRepository< Company, Long >{

}

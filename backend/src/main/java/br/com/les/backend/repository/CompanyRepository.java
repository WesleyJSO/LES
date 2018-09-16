package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Company;

public interface CompanyRepository extends JpaRepository< Company, Long >{

	List<Company> findByActive( boolean active );

}

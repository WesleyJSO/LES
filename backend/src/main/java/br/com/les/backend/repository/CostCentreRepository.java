package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.CostCentre;

public interface CostCentreRepository extends JpaRepository < CostCentre, Long > {
	
	List<CostCentre> findByActive( boolean active );

}

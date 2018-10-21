package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.les.backend.entity.Request;

public interface RequestRepository extends JpaRepository < Request, Long > {

}

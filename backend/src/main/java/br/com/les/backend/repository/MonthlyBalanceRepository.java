package br.com.les.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.les.backend.entity.MonthlyBalance;

public interface MonthlyBalanceRepository extends GenericRepository<MonthlyBalance>{

	@Query("select t from MonthlyBalance t where t.balance != 0 or t.month = ?1 order by t.creationDate")
	List< MonthlyBalance > findWithPendency(String yearMonth);
}
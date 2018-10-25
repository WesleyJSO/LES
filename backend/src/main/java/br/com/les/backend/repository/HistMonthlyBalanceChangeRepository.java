package br.com.les.backend.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.les.backend.entity.HistMonthlyBalanceChange;

public interface HistMonthlyBalanceChangeRepository extends GenericRepository<HistMonthlyBalanceChange> {
	
	@Transactional
    @Modifying
	@Query("delete from HistMonthlyBalanceChange h where h.amount = 0")
	void deleteDeprecated();

}
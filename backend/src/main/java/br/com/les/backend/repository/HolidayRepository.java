package br.com.les.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import br.com.les.backend.entity.Holiday;

@Component
public interface HolidayRepository extends GenericRepository<Holiday>{

	@Query("select t from Holiday t where t.date >= ?1 and t.date <= ?2")
	List< Holiday > findByYear(LocalDate fistOfTheYear, LocalDate lastOfTheYear);
	
	@Query("select t from Holiday t where t.date = ?1")
	List< Holiday > findBydate(LocalDate date);
}

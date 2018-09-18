package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.CostCentreDAO;
import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.entity.Employee;
import br.com.les.backend.repository.CostCentreRepository;
import br.com.les.backend.repository.EmployeeRepository;

@Service
@Transactional
public class CostCentreService implements IService {
	
	@Autowired
	private CostCentreRepository costCentreRepository;
	
	@Autowired
	private CostCentreDAO costCentreDAO;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DomainEntity save(DomainEntity entity) {
			
		DomainEntity ent = costCentreRepository.save( ( CostCentre ) entity );
		CostCentre c = ( CostCentre ) entity;
		if ( null != c.getEmployees() && !c.getEmployees().isEmpty() ) {
			for (Employee e : c.getEmployees() ) {
				e.setCostCentre( ( CostCentre ) entity );
				employeeRepository.save( e );
			}
		} else {
			List < Employee > l =  employeeRepository.findAll();
			for ( Employee e : l) {
				if ( null != e.getCostCentre() ) {
					if ( e.getCostCentre().getId() == c.getId() ) {
						e.setCostCentre( null );
						employeeRepository.save( e );
					}
				}
			}
		}
		return ent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends DomainEntity> List<T> findAll() {
		return ( List<T> ) costCentreRepository.findByActive( true );
	}
	
	public void deleteById ( long id ) {
		costCentreRepository.deleteById( id );
	}

	@Override
	public List<DomainEntity> findByParameters( DomainEntity entity ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends DomainEntity> int softDelete(T entity) {
		int i  =  costCentreDAO.softDelete( entity );
		List < Employee > l =  employeeRepository.findAll();
		for ( Employee e : l) {
			e.setCostCentre( null );
			employeeRepository.save( e );
		}
		return i;
	}
	
	

}

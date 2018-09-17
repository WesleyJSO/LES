package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.repository.CostCentreRepository;

@Service
@Transactional
public class CostCentreService implements IService {
	
	@Autowired
	private CostCentreRepository costCentreRepository;

	@Override
	public DomainEntity save(DomainEntity entity) {
		return costCentreRepository.save( ( CostCentre ) entity );
	}

	@SuppressWarnings("unchecked")
	@Override
	public List< CostCentre > findAll() {
		return costCentreRepository.findAll();
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
	public void delete(DomainEntity entity) {
		costCentreRepository.deleteById( entity.getId() );
	}

}

package br.com.les.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.les.backend.dao.AppointmentDAO;
import br.com.les.backend.entity.Appointment;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentService implements IService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private AppointmentDAO appointmentDAO;
	
	@Override
	public DomainEntity save(DomainEntity entity) {
		return appointmentRepository.save( ( Appointment ) entity );
	}

	@Override
	public List<DomainEntity> findByParameters(DomainEntity entity) {

		List< DomainEntity > resultList = appointmentDAO.findByDay( (Appointment) entity );
		if ( resultList.isEmpty() ) {
			Appointment appointment = new Appointment();
			appointment.setDate();
			resultList.add(appointment);
		}
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends DomainEntity> List<T> findAll() {
		return (List<T>) appointmentRepository.findAll();
	}

	@Override
	public <T extends DomainEntity> int softDelete(T entity) {
		return appointmentDAO.softDelete(entity);
	}

}

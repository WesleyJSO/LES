package br.com.les.backend.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Time;
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
		Appointment a = ( Appointment ) entity;
		LocalTime balance = LocalTime.MIN;
		LocalTime left = LocalTime.of(8, 0);
		if ( a.getMorningOut() != null ) {
			balance = balance.plusNanos( (a.getMorningOut().minusNanos(a.getMorningEntrance().toNanoOfDay())).toNanoOfDay() );			
		}
		if ( a.getAfternoonOut() != null ) {
			balance = balance.plusNanos( (a.getAfternoonOut().minusNanos(a.getAfternoonEntrance().toNanoOfDay())).toNanoOfDay() );			
		}
		if ( a.getNightOut() != null ) {
			balance = balance.plusNanos( (a.getNightOut().minusNanos(a.getNightEntrance().toNanoOfDay())).toNanoOfDay() );
		}
		if ( a.getParticularExitReturn() != null ) {
			balance = balance.minusNanos( (a.getParticularExitReturn().minusNanos(a.getParticularExit().toNanoOfDay())).toNanoOfDay() );			
		}
		if ( balance.isBefore( left ) ) {
			a.setHoursLeft( left.minusNanos( balance.toNanoOfDay() ) );
			a.setDayOvertime( LocalTime.MIN );
		} else {
			a.setHoursLeft( LocalTime.MIN );
			a.setDayOvertime( balance.minusNanos( left.toNanoOfDay() ) );
		}
		a.setBalance(balance);
		return appointmentRepository.save( ( Appointment ) entity );
	}

	@Override
	public List<DomainEntity> findByParameters(DomainEntity entity) {

		Date d = new Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String today = form.format(d);
		List< DomainEntity > resultList = appointmentRepository.findByDate( today );
		if ( resultList.isEmpty() ) {
			Appointment appointment = new Appointment();
			appointment.setId(1l);
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

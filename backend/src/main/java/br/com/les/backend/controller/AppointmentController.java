package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.Appointment;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class AppointmentController extends AbstractController {
	
	@GetMapping(value = "/appointments" )
	public Result findAll( @PathVariable Long employeeId ) {
		return run(Actions.FIND_ALL.getValue()).execute(new Appointment( employeeId ), Actions.FIND_ALL.getValue());
		// return facade.findAll( new Company( true ), getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value="/appointment" )
	public Result save( @RequestBody Appointment appointment ) {
		return run(Actions.SAVE.getValue()).execute(appointment, Actions.SAVE.getValue());
		// return facade.execute( ( DomainEntity ) company, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value="/appointment" )
	public Result update( @RequestBody Appointment appointment ) {
		return run(Actions.UPDATE.getValue()).execute(appointment, Actions.UPDATE.getValue());
		// return facade.update( company, getMethodName( new Object() {} ) );
	}
	
	@GetMapping(value = "/appointment/{employeeId}" )
	public Result findByDay( @PathVariable Long employeeId ) {
		return run(Actions.SEARCH.getValue()).execute(new Appointment( employeeId ), getMethodName( new Object() {} ));
		// return facade.delete( new Company( id ), getMethodName( new Object() {} ) );
	}
}

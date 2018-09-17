package br.com.les.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.les.backend.entity.CostCentre;
import br.com.les.backend.entity.DomainEntity;
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class CostCentreController extends AbstractController {
	
	@GetMapping( value = "/CentrosDeCustos" )
	public Result findAll () {
		return facade.findAll( new CostCentre(), getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value = "/CentroDeCusto" )
	public Result save ( @RequestBody CostCentre costCentre ) {
		return facade.save( ( DomainEntity ) costCentre , getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value = "/CentroDeCustos/{centreCostId}" )
	public Result update(@RequestBody CostCentre costCentre) {
		return facade.update( ( DomainEntity ) costCentre, getMethodName(new Object() {} ) );
	}

    @DeleteMapping( value = "CentroDeCusto/{centreCostId}" )
	public Result delete ( @PathVariable Long centreCostId) {
		return facade.delete( ( DomainEntity ) new CostCentre( centreCostId ), getMethodName( new Object() {} ) );
	}
	
}

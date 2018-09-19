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
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@CrossOrigin
@RestController
public class CostCentreController extends AbstractController {
	
	@GetMapping( value = "/CentrosDeCustos" )
	public Result findAll () {
		return run(Actions.FIND_ALL.getValue()).execute(new CostCentre(), Actions.FIND_ALL.getValue());
		// return facade.findAll( new CostCentre(), getMethodName( new Object() {} ) );
	}
	
	@PostMapping( value = "/CentroDeCusto" )
	public Result save ( @RequestBody CostCentre costCentre ) {
		return run(Actions.SAVE.getValue()).execute(costCentre, Actions.SAVE.getValue());
		// return facade.save( ( DomainEntity ) costCentre, getMethodName( new Object() {} ) );
	}
	
	@PutMapping( value = "/CentroDeCustos/{centreCostId}" )
	public Result update(@RequestBody CostCentre costCentre) {
		return run(Actions.UPDATE.getValue()).execute(costCentre, Actions.UPDATE.getValue());
		// return facade.update( ( DomainEntity ) costCentre, getMethodName(new Object() {} ) );
	}

    @DeleteMapping( value = "CentroDeCusto/{centreCostId}" )
	public Result delete ( @PathVariable Long centreCostId) {
    	return run(Actions.DELETE.getValue()).execute(new CostCentre( centreCostId ), Actions.DELETE.getValue());
		// return facade.delete( ( DomainEntity ) new CostCentre( centreCostId ), getMethodName( new Object() {} ) );
	}
	
}

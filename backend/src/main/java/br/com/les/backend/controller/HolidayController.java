package br.com.les.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.les.backend.entity.Holiday;
import br.com.les.backend.navigator.BusinessCase;
import br.com.les.backend.navigator.BusinessCaseBuilder;
import br.com.les.backend.utils.Actions;
import br.com.les.backend.utils.Result;

@Controller
@RequestMapping("${server.controller.prefix}holiday")
public class HolidayController extends AbstractController<Holiday> {

	public HolidayController() {
		super(Holiday.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	@DeleteMapping(path = "/{id}/{clazzName}")
    public @ResponseBody Result<Holiday> delete(@PathVariable("id") Long id, @PathVariable("clazzName") String clazzName) throws InstantiationException, IllegalAccessException {

    	try {
			clazz = (Class<? extends Holiday>) Class.forName(clazzName).newInstance();

			BusinessCase<Holiday> bCase = new BusinessCaseBuilder<Holiday>()
				.withName(existingNavigation("DELETE_".concat(clazz.getSimpleName().toUpperCase())))
				.build();
    	
			Holiday entity = null;
			entity = (Holiday) Class.forName(clazzName).newInstance();
			return run(Actions.DELETE.getValue()).execute(entity, bCase);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    }
}

package br.com.les.backend.controller;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.net.HttpHeaders;

import br.com.les.backend.entity.TimeTracking;
import br.com.les.backend.navigator.BusinessCase;
import br.com.les.backend.navigator.BusinessCaseBuilder;
import br.com.les.backend.navigator.INavigator;

@Controller
@RequestMapping("${server.controller.prefix}timetracking")
public class TimeTrackingController extends AbstractController<TimeTracking> {
	
	@Autowired private INavigator<TimeTracking> navigator;
	
	public TimeTrackingController() {
		super(TimeTracking.class);
	}
	
	@GetMapping(value = "{employeeId}/{monthAndYear}")
	public @ResponseBody ResponseEntity<byte[]> findPDF(@PathVariable Long employeeId, @PathVariable String monthAndYear) throws InstantiationException, IllegalAccessException {
		
		try {
			
			BusinessCase<TimeTracking> bCase = new BusinessCaseBuilder<TimeTracking>()
					.withName(existingNavigation("FIND_".concat(clazz.getSimpleName().toUpperCase())))
					.build();
			
			navigator.run( new TimeTracking(employeeId , LocalDate.parse(monthAndYear)), bCase);
			
			if ( !bCase.isSuspendExecution() ) {
				// get PDF generated in reports folder
				File file = new File("reports/timeTracking.pdf");
				// set PDF bytes array in response
				return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("application/pdf"))
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + "timeTracking.pdf" + "\"")
	            .body(Files.readAllBytes(file.toPath()));
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

}

package ro.sci.ems.web;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.ems.service.EmployeeService;
import ro.sci.ems.web.view.Doctor;
import ro.sci.ems.web.view.Doctor2HospitalAssignment;
import ro.sci.ems.web.view.Hospital;

@Controller
@RequestMapping("/test-select")
public class TestSelectController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@RequestMapping("")
	public ModelAndView renderPage() {
		ModelAndView modelAndView = new ModelAndView("test_select");
		
		List<Doctor> doctors = new LinkedList<>();
		doctors.add(new Doctor(1, "Doctor", "One"));
		doctors.add(new Doctor(2, "Doctor", "Two"));
		
		modelAndView.addObject("doctors", doctors);
		
		
		List<Hospital> hospitals = new LinkedList<>();
		hospitals.add(new Hospital(1, "H  One"));
		hospitals.add(new Hospital(2, "H Two"));
		modelAndView.addObject("hospitals", hospitals);
		modelAndView.addObject("doctor2HospitalAssignment", new Doctor2HospitalAssignment());
		
		
		
		return modelAndView;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSave(Doctor2HospitalAssignment doctor2HospitalAssignment) {
		LOGGER.debug("Doctor2HospitalAssignment: " + doctor2HospitalAssignment);
		ModelAndView result =  renderPage();
		result.addObject("doctor2HospitalAssignment", doctor2HospitalAssignment);
		return result;
	}
}

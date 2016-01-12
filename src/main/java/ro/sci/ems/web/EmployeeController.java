package ro.sci.ems.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.ems.domain.Employee;
import ro.sci.ems.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("")
	public ModelAndView index() {
		
		Collection<Employee> allEmployees = employeeService.listAll();
		
		ModelAndView modelAndView  = new ModelAndView("employee_list");
		modelAndView.addObject("allEmployees", allEmployees);
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=add")
	public String add() {
		return "employee_edit";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "employee_edit";
	}
}

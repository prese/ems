package ro.sci.ems.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@RequestMapping("")
	public String index() {
		return "employee_list";
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

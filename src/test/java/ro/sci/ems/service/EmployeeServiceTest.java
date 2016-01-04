package ro.sci.ems.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ems.Application;
import ro.sci.ems.domain.Employee;
import ro.sci.ems.domain.Gender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@After
	public void tearDown() {
		Collection<Employee> employees = new LinkedList<Employee>(employeeService.listAll());

		for (Employee employee : employees) {
			employeeService.delete(employee.getId());
		}
	}

	@Test
	public void test_empty_get_all() {
		Collection<Employee> employees = employeeService.listAll();
		Assert.assertTrue(employees.isEmpty());
	}

	@Test(expected = ValidationException.class)
	public void test_add_no_first_name() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setLastName("Babanan");
		employeeService.save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_last_name() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		employeeService.save(em);

	}
	
	
	@Test(expected = ValidationException.class)
	public void test_add_too_young() throws ValidationException {
		Employee em = new Employee();
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 17);
		em.setBirthDate(c.getTime());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		employeeService.save(em);

	}
	
	
	@Test(expected = ValidationException.class)
	public void test_add_too_old() throws ValidationException {
		Employee em = new Employee();
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, 1900);
		em.setBirthDate(c.getTime());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		employeeService.save(em);

	}
	
	
	
	@Test(expected = ValidationException.class)
	public void test_add_too_employment_date_before_birth_date() throws ValidationException {
		Employee em = new Employee();
		Calendar c = GregorianCalendar.getInstance();
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 20);
		em.setBirthDate(c.getTime());
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 21);
		em.setEmploymentDate(c.getTime());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		employeeService.save(em);

	}
	
	

	@Test(expected = ValidationException.class)
	public void test_add_no_birthdate() throws ValidationException {
		Employee em = new Employee();
		// em.setBirthDate(new Date());
		em.setEmploymentDate(new Date(70,1,1));
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_employment_date() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		// em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_gender() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		// em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_job_title() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date());
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		// em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);

	}

	@Test(expected = ValidationException.class)
	public void test_add_empty() throws ValidationException {
		Employee em = new Employee();

		employeeService.save(em);

	}

	@Test
	public void test_add_valid_employee() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);
		Assert.assertEquals(1, employeeService.listAll().size());
		Employee fromDB = employeeService.listAll().iterator().next();
		Assert.assertNotNull(fromDB);
		Assert.assertTrue(fromDB.getId() > 0);
		em.setId(fromDB.getId());
		Assert.assertEquals(em, fromDB);
	}

	@Test
	public void test_delete_inexistent() throws ValidationException {

		Assert.assertFalse(employeeService.delete(-10l));

	}

	@Test
	public void test_add_delete() throws Exception {
		Employee em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);
		Assert.assertEquals(1, employeeService.listAll().size());
		Employee fromDB = employeeService.listAll().iterator().next();

		Assert.assertTrue(employeeService.delete(fromDB.getId()));
		Assert.assertFalse(employeeService.delete(fromDB.getId()));
		Assert.assertEquals(0, employeeService.listAll().size());

	}

	@Test
	public void test_search_by_name_no_result() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Babanan");
		em.setLastName("Gogu");
		employeeService.save(em);
		Assert.assertEquals(0, employeeService.search("cucu").size());

	}

	@Test
	public void test_search_by_name_multiple_results() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Gogu");
		employeeService.save(em);

		em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Cucu");
		em.setLastName("Gogu");
		employeeService.save(em);
		Assert.assertEquals(2, employeeService.search("Gogu").size());

	}

	@Test
	public void test_search_by_name_multiple_results_partial_search() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Gogu");
		employeeService.save(em);

		em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Cucu");
		em.setLastName("Gogu");
		employeeService.save(em);
		Assert.assertEquals(2, employeeService.search("Gog").size());

	}

	@Test
	public void test_search_by_name_multiple_results_partial_case_insensitive() throws ValidationException {
		Employee em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Gogu");
		employeeService.save(em);

		em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Mariana");
		em.setLastName("Cucu");
		employeeService.save(em);

		em = new Employee();
		em.setBirthDate(new Date(70,1,1));
		em.setEmploymentDate(new Date());
		em.setGender(Gender.FEMALE);
		em.setJobTitle("Ana");
		em.setFirstName("Cucu");
		em.setLastName("gogu");
		employeeService.save(em);
		Assert.assertEquals(2, employeeService.search("gOg").size());

	}

}

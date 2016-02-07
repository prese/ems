package ro.sci.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.ems.dao.EmployeeDAO;
import ro.sci.ems.dao.db.JDBCEmployeeDAO;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
	
	@Bean
	public EmployeeDAO employeeDao() {
		return //new JDBCEmployeeDAO("localhost", "5432", "test", "test", "test");
				new JDBCEmployeeDAO("ec2-54-235-152-114.compute-1.amazonaws.com",
				"5432", "debvjrj4023nfd",
				"ypsrfyzvblpwsg", 
				"ISY2KvbIISXDHjwdVd-7egvk2o");
				
				
				
	}

}

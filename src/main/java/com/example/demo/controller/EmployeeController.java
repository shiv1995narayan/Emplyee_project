package com.example.demo.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.Exception.EmployeeException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController 
{
	@Autowired
    EmployeeService service;
	
	@PostMapping("/insert")
	public Employee inserData(@RequestParam(name="emp") String emp) throws JsonMappingException, IOException,JsonProcessingException, EmployeeException
	{
		Employee employee = new ObjectMapper().readValue(emp , Employee.class);
		return service.insert(employee);
	}
	@GetMapping("/getAllEmployees")
	public List<Employee> retrive() throws EmployeeException
	{
		return service.retrieve();
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(name = "id") int id) throws EmployeeException
	{
		 service.delete(id);
	}
	
	@PostMapping("/update")
	public String update(@RequestBody Employee employee) throws EmployeeException
	{
		service.update(employee);
		return "Record updated successfully";
	}
	
	
	@Bean
	public CorsFilter corsFilter()
	{
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("put");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	
}

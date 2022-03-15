package com.example.demo.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.Exception.EmployeeException;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;



@Service
public class EmployeeService
{
	public static final String SPECIAL_CHARACTER_STRING = "[+=&,?{}~`'<>$%\\^;\"!#*@]";
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	private EmployeeDao employeeDao;

	public Employee insert(Employee employee) throws EmployeeException {
		Employee existEmployees=new Employee();
		isFieldNameEmptyOrNot(employee);
		Employee existEmployee=employeeDao.checkExistName(employee.getName(),employee.getEmail());
		if(existEmployee==null) {
			existEmployees.setEmail(employee.getEmail());
			existEmployees.setExperiance(employee.getExperiance());
			existEmployees.setName(employee.getName());
			existEmployees.setPassword(employee.getPassword());
			existEmployees.setRole(employee.getRole());
			return empRepository.save(existEmployees);
		}else {
			throw new EmployeeException("Employee already exist with name and email");
		}
	}

	public List<Employee> retrieve() 
	{
		return empRepository.findAll();
	}

	public void delete(int id) {
		empRepository.deleteById(id);
	}

	public String update(Employee employee) throws EmployeeException {
		isFieldNameEmptyOrNot(employee);
		empRepository.save(employee);
		return "Record updated successfully";
	}
	
	private void isFieldNameEmptyOrNot(Employee employee) throws EmployeeException {
		if(StringUtils.isEmpty(employee.getName())) {
			throw new EmployeeException("EMPLOYEE_NAME_CAN'T_BE_EMPTY");
		}
		if (Boolean.TRUE.equals(isContainsSpecialCharacters(employee.getName()))) {
			throw new EmployeeException("EMPLOYEE_NAME_NOT_ALLOW_SPECIAL_CHARACTER");
		}
		
		if(StringUtils.isEmpty(employee.getExperiance())) {
			throw new EmployeeException("EMPLOYEE_EXPERIANCE_CAN'T_BE_EMPTY");
		}
		if(StringUtils.isEmpty(employee.getPassword())) {
			throw new EmployeeException("EMPLOYEE_PASSWORD_CAN'T_BE_EMPTY");
		}
		if(StringUtils.isEmpty(employee.getRole())) {
			throw new EmployeeException("EMPLOYEE_ROLE_CAN'T_BE_EMPTY");
		}
		
		if(StringUtils.isEmpty(employee.getEmail())) {
			throw new EmployeeException("EMPLOYEE_EMAIL_CAN'T_BE_EMPTY");	
		}
		else if(!isValidEmailId(employee.getEmail())) {
			throw new EmployeeException("EMAILID SHOULD HAVE @ AND . AND ONLY ALLOW _");
		}
		
		
	}
	public static Boolean isContainsSpecialCharacters(String s) {
		try {
			Pattern p = Pattern.compile(SPECIAL_CHARACTER_STRING);
			Matcher m = p.matcher(s);
			return m.find();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValidEmailId(String emailStr) {
		Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		        return matcher.find();
		}
}

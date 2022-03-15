package com.example.demo.dao;

import com.example.demo.model.Employee;

public interface EmployeeDao extends IGenericDao<Integer, Employee>{

	 Employee checkExistName(String name, String email);
  
}

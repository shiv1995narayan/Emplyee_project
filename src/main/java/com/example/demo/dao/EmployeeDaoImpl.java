package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

import ch.qos.logback.classic.Logger;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	@PersistenceContext
	EntityManager entityManager;

	Logger logger = (Logger) LoggerFactory.getLogger(EmployeeDaoImpl.class);
	@Override
	public Employee checkExistName(String name, String email) {
		try {
			logger.info("inside method checkExistName");
			Query query = entityManager.createNamedQuery("checkExistName").setParameter("name", name).setParameter("email", email);
			return (Employee) query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

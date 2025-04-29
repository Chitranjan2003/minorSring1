package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nt.model.Employee;
import com.nt.service.IEmployeeService;

@Controller("payroll")
public class PayrollOperationsController {

	@Autowired
	private IEmployeeService service;
	
	public List<Employee> showEmployeesByDesgs(String desg1,String desg2,String desg3)throws Exception{
		
		List<Employee> list =service.fetchAAllEmployeesDesgs(desg1, desg2, desg3);
		return list;
	}
	
	
	public String ProcessEmployeeForRegistration(Employee emp)throws Exception{
		String msg=service.registerEmployee(emp);
		return msg;
	}
	
}

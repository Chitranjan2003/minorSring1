package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.IEmployeeDAO;
import com.nt.model.Employee;

@Service("empService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDAO dao;
	
	@Override
	public List<Employee> fetchAAllEmployeesDesgs(String desg1, String desg2, String desg3) throws Exception {
		
		desg1=desg1.toUpperCase();
		desg2=desg2.toUpperCase();
		desg3=desg3.toUpperCase();
		
		List<Employee> list =dao.getEmployeesByDesgs(desg1, desg2, desg3);
		
		
		list.sort((t1,t2)->t1.getEmpno().compareTo(t2.getEmpno()));
	
		list.forEach(emp->{
			
			emp.setGrossSalary(emp.getSalary()+(emp.getSalary()*0.4));
			emp.setNetSalary(emp.getGrossSalary()-(emp.getGrossSalary()*0.2));
		});

		
		
		
		return list;
	}

	@Override
	public String registerEmployee(Employee emp) throws Exception {
		int count =dao.insertEmployee(emp); 

		
		return count==0?"Employee is not register":"Employee is register";
	}

}

package com.nt.service;

import java.util.List;

import com.nt.model.Employee;

public interface IEmployeeService {

	public List<Employee> fetchAAllEmployeesDesgs(String desg1,String desg2,String desg3)throws Exception;

	public String registerEmployee(Employee emp)throws Exception;
}

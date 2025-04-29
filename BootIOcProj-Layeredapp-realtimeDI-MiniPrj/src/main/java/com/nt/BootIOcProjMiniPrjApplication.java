package com.nt;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nt.controller.PayrollOperationsController;
import com.nt.model.Employee;

@SpringBootApplication
public class BootIOcProjMiniPrjApplication {

  

	public static void main(String[] args) {
    ApplicationContext ctx=SpringApplication.run(BootIOcProjMiniPrjApplication.class, args);
	PayrollOperationsController controller=ctx.getBean("payroll",PayrollOperationsController.class);
	Scanner sc=new Scanner(System.in);
	System.out.println("Inset the data...........");
	System.out.println("enetr emp name");
	String name=sc.next();
	System.out.println("enetr emp desg");
	String desg=sc.next();
	System.out.println("enetr emp salary");
	Double salary=sc.nextDouble();
	System.out.println("enetr emp deptno(10,20,30,40)");
	int deptno=sc.nextInt();
	
	Employee emp1 =new Employee();
	emp1.setEname(name);
	emp1.setDesg(desg);
	emp1.setSalary(salary);
	emp1.setDeptno(deptno);
	
	try {
		String msg=controller.ProcessEmployeeForRegistration(emp1);
		System.out.println(msg);
	}catch(Exception e){
	 e.printStackTrace();	
	}
	
	
	System.out.println("getting Employee details........");
	
	try {
	List<Employee> list=controller.showEmployeesByDesgs("CLERK", "MANAGER", "SALESMAN");
	
	list.forEach(emp->{
		System.out.println(emp);
	});
	
	}catch (Exception e) {
		e.printStackTrace();
	} 
	
	}

}

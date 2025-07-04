package com.nt.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.model.Employee;


@Repository("empDAO")
public class EmployeeDAOImpl implements IEmployeeDAO {
	
	private static final String GET_EMPS_BY_DESGS="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM emp WHERE JOB IN(?,?,?)ORDER BY JOB";
	private static final String INSERT_EMP_QUERY="INSERT INTO emp(EMPNO,ENAME,JOB,SAL,DEPTNO) VALUES(emp_seq.NEXTVAL,?,?,?,?)";

	@Autowired
	private DataSource ds;
	
	@Override
	public List<Employee> getEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception {
		 
		List<Employee> list=null;
		
		
		
		try(	Connection con=ds.getConnection();
				PreparedStatement ps=con.prepareStatement(GET_EMPS_BY_DESGS);	
				){
			
			ps.setString(1, desg1);
			ps.setString(2, desg2);
			ps.setString(3, desg3);
			
			//execute sql query
			try(ResultSet rs=ps.executeQuery()){
				
				list=new ArrayList();
				while(rs.next()) {
					Employee emp=new Employee();
					emp.setEmpno(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setDesg(rs.getString(3));
					emp.setSalary(rs.getDouble(4));
					emp.setDeptno(rs.getInt(5));
					
					list.add(emp);
				}
			}
			
		}catch(SQLException se){
			se.printStackTrace();
			
			throw se;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	 return list;
	}

	@Override
	public int insertEmployee(Employee emp) throws Exception {
		int result=0;
		try(
				Connection con=ds.getConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_QUERY);
				){
			
			ps.setString(1,emp.getEname());
			ps.setString(2, emp.getDesg());
			ps.setDouble(3, emp.getSalary());
			ps.setInt(4,emp.getDeptno());
			
		 result=ps.executeUpdate();
			
			return result;
		}
		catch(SQLException se) {
			se.printStackTrace();
			throw se;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

}

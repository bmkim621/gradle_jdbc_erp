package gradle_jdbc_erp.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll() throws SQLException;
	
	int insertEmployee(Employee item) throws SQLException;
	
	int deleteEmployee(Employee item) throws SQLException;
	
	int updateEmployee(Employee item) throws SQLException;
	
	String nextEmpNo() throws SQLException;
}

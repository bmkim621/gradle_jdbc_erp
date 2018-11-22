package gradle_jdbc_erp.service;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dao.EmployeeDao;
import gradle_jdbc_erp.dao.EmployeeDaoImpl;
import gradle_jdbc_erp.dto.Employee;

public class EmployeeUIService {
	private EmployeeDao empDao;

	public EmployeeUIService() {
		empDao = new EmployeeDaoImpl();
	}
	
	public List<Employee> selectAll() throws SQLException {
		return empDao.selectEmployeeByAll();
	}
	
	public String nextEmpNo() throws SQLException {
		return empDao.nextEmpNo();
	}
	
	public int addEmp(Employee item) throws SQLException{
		return empDao.insertEmployee(item);
	}
	
	public int updateEmp(Employee item) throws SQLException{
		return empDao.updateEmployee(item);
	}
	
	public int deleteEmp(Employee item) throws SQLException{
		return empDao.deleteEmployee(item);
	}
	

}

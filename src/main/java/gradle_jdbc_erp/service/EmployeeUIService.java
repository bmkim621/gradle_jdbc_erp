package gradle_jdbc_erp.service;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dao.DepartmentDao;
import gradle_jdbc_erp.dao.DepartmentDaoImpl;
import gradle_jdbc_erp.dao.EmployeeDao;
import gradle_jdbc_erp.dao.EmployeeDaoImpl;
import gradle_jdbc_erp.dao.TitleDao;
import gradle_jdbc_erp.dao.TitleDaoImpl;
import gradle_jdbc_erp.dto.Employee;

public class EmployeeUIService {
	private EmployeeDao empDao;
	private DepartmentDao deptDao;
	private TitleDao titleDao;

	public EmployeeUIService() {
		empDao = new EmployeeDaoImpl();
		deptDao = new DepartmentDaoImpl();
		titleDao = new TitleDaoImpl();
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

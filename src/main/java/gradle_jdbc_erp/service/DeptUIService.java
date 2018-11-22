package gradle_jdbc_erp.service;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dao.DepartmentDao;
import gradle_jdbc_erp.dao.DepartmentDaoImpl;
import gradle_jdbc_erp.dto.Department;

public class DeptUIService {
	private DepartmentDao deptDao;

	public DeptUIService() {
		deptDao = new DepartmentDaoImpl();
	}
	
	public List<Department> selectAll() throws SQLException {
		return deptDao.selectDepartmentByAll();
	}
	
	public String nextDeptNo() throws SQLException {
		return deptDao.nextDeptNo();
	}
	
	public int addDept(Department item) throws SQLException{
		return deptDao.insertDepartment(item);
	}
	
	public int updateDept(Department item) throws SQLException{
		return deptDao.updateDepartment(item);
	}
	
	public int deleteDept(Department item) throws SQLException{
		return deptDao.deleteDepartment(item);
	}
}

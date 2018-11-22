package gradle_jdbc_erp.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll() throws SQLException;
	
	int insertDepartment(Department item) throws SQLException;
	
	int deleteDepartment(Department item) throws SQLException;
	
	int updateDepartment(Department item) throws SQLException;
	
	String nextDeptNo() throws SQLException;
}

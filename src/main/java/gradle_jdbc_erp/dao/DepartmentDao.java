package gradle_jdbc_erp.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll() throws SQLException;
	
	int insertDepartment(Department dept) throws SQLException;
	
	int deleteDepartment(Department dept) throws SQLException;
	
	int updateDepartment(Department dept) throws SQLException;
	
	Department selectDepartmentByCode(Department dept) throws SQLException;
}

package gradle_jdbc_erp.dao;

import java.sql.SQLException;
import java.util.List;

import gradle_jdbc_erp.dto.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	
	//추가
	int insertDepartment(Department department) throws SQLException;
	
	//수정
	int updateDepartment(Department department) throws SQLException;
	
	//삭제
	int deleteDepartment(Department department) throws SQLException;
	
	//검색
	Department selectDepartmentByNo(Department department) throws SQLException;

}

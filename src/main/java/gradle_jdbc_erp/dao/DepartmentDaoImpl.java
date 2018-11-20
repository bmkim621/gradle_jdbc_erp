package gradle_jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.jdbc.ConnectionProvider;
import gradle_jdbc_erp.jdbc.LogUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public List<Department> selectDepartmentByAll() throws SQLException {
		List<Department> list = new ArrayList<>();
		String sql = "select deptno, deptname, floor from department";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		String deptNo = rs.getString("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		
		return new Department(deptNo, deptName, floor);
	}


	@Override
	public int insertDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Department selectDepartmentByCode(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

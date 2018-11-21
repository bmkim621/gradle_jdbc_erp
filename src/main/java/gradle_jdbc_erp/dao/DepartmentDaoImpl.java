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
	public int insertDepartment(Department item) throws SQLException {
		String sql = "insert into department values (?, ?, ?)";
		int res = 0;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, item.getDeptNo());
			pstmt.setString(2, item.getDeptName());
			pstmt.setInt(3, item.getFloor());
			
			LogUtil.prnLog(pstmt);
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int deleteDepartment(Department item) throws SQLException {
		String sql = "delete from department where deptno = ?";
		int res = 0;
		
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, item.getDeptNo());
			LogUtil.prnLog(pstmt);
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public int updateDepartment(Department item) throws SQLException {
		String sql = "update department set deptname = ?, floor = ? where deptno = ?";
		int res = 0;
		
		try(Connection conn = ConnectionProvider.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, item.getDeptName());
			pstmt.setInt(2, item.getFloor());
			pstmt.setString(3, item.getDeptNo());
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Department selectDepartmentByCode(Department item) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String nextDeptNo() throws SQLException {
		String sql = "select max(deptno) as nextno from department";
		String nextStr = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("D%03d", Integer.parseInt(rs.getString("nextno").substring(1)) + 1);
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		return nextStr;
	}

}

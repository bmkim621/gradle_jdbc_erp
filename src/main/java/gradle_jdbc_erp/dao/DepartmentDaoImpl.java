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

	// ================ 검색 ====================
	@Override
	public List<Department> selectDepartmentByAll() {
		//DB에서 검색한 결과를 ArrayList에 담는다.
		List<Department> list = new ArrayList<>();
		
		//SQL문(DB에 있는 속성으로)
		String sql = "select deptno, deptname, floor from department";
		//1. 자바를 DB에 연결 Connection~
		try(Connection conn = ConnectionProvider.getConnection();
				//2. SQL문 준비
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//3. Select 결과 가지고오기
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			while(rs.next()) {
				list.add(getDepartment(rs));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	//Department의 값들을 가지고온다.
	private Department getDepartment(ResultSet rs) throws SQLException {
									//DB의 attribute와 같도록 한다.
		String deptNo = rs.getString("deptno"); //번호
		String deptName = rs.getString("deptname");	//부서명
		int floor = rs.getInt("floor");	//위치
		
		return new Department(deptNo, deptName, floor);
	}

	// ================ 추가 ====================
	@Override
	public int insertDepartment(Department department) throws SQLException {
		//SQL문(DB에 있는 속성으로)
		String sql = "insert into department values(?, ?, ?)";
		int res = 0;
		
		//1. 자바를 DB에 연결 Connection~
		try(Connection conn = ConnectionProvider.getConnection();
				//2. SQL문 준비
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			//매개변수는 0이 아니라 1부터 시작
			//1: 번호, 2: 부서명, 3: 위치
			pstmt.setString(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			
			//값이 제대로 추가됐는지 확인하기
			LogUtil.prnLog(pstmt);
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	
	// ================ 수정 ====================
	@Override
	public int updateDepartment(Department department) throws SQLException {
		//SQL문(DB에 있는 속성으로)
		String sql = "update department set deptname = ?, floor = ? where deptno = ?";
		int res = 0;
		
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			//매개변수는 0이 아니라 1부터 시작
			//1: 부서명, 2: 위치, 3: 번호(SQL문 순서대로)
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getFloor());
			pstmt.setString(3, department.getDeptNo());
			//확인하기
			LogUtil.prnLog(pstmt);
			
			res = pstmt.executeUpdate();
			
		}
		return res;
	}

	// ================ 삭제 ====================
	@Override
	public int deleteDepartment(Department department) throws SQLException {
		//SQL문(DB에 있는 속성으로)
		String sql = "delete from department where deptno = ?";
		int res = 0;
		
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			//번호만
			pstmt.setString(1, department.getDeptNo());
			//확인
			LogUtil.prnLog(pstmt);
			
			res = pstmt.executeUpdate();
		}
		return res;
	}

	@Override
	public Department selectDepartmentByNo(Department department) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

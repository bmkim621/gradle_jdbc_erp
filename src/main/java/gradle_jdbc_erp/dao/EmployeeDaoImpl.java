package gradle_jdbc_erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.dto.Employee;
import gradle_jdbc_erp.dto.Gender;
import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.jdbc.ConnectionProvider;
import gradle_jdbc_erp.jdbc.LogUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		List<Employee> list = new ArrayList<>();
		String sql = "select empno, empname, e.titleno as titleno, salary, gender, d.deptno as deptno, joindate, deptname, floor, titlename "
				+ "from employee e join department d on e.deptno = d.deptno join title t on e.titleno = t.titleno";
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			while(rs.next()) {
				list.add(getEmployee(rs));
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		String empNo = rs.getString("empno");
		String empName = rs.getString("empname");
		Title titleNo = new Title(rs.getString("titleno"), rs.getString("titlename"));
		int salary = rs.getInt("salary");
		Gender gender = rs.getInt("gender") == 0 ? Gender.FEMALE : Gender.MALE;
		Department deptNo = new Department(rs.getString("deptno"), rs.getString("deptname"), rs.getInt("floor"));
		Date joinDate = rs.getDate("joindate");
		
		return new Employee(empNo, empName, titleNo, salary, gender, deptNo, joinDate);
	}

	@Override
	public int insertEmployee(Employee item) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(Employee item) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee item) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nextEmpNo() throws SQLException {
		String currentDate = LocalDate.now().getYear() + "";	//현재 날짜의 연도
		
		String sql = "select max(empno) as nextno, empname, e.titleno as titleno, salary, gender, d.deptno as deptno, joindate, deptname, floor, titlename "
				+ "from employee e join department d on e.deptno = d.deptno join title t on e.titleno = t.titleno";
		String nextStr = null;
		try(Connection conn = ConnectionProvider.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			LogUtil.prnLog(pstmt);
			if(rs.next()) {
				nextStr = String.format("E%3s%03d", (String) currentDate.substring(1), Integer.parseInt(rs.getString("nextno").substring(4)) + 1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return nextStr;
	}

}

package gradle_jdbc_erp;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_erp.dao.DepartmentDao;
import gradle_jdbc_erp.dao.DepartmentDaoImpl;
import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.jdbc.ConnectionProvider;
import gradle_jdbc_erp.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static DepartmentDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("START DepartmentDaoTest");
		dao = new DepartmentDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("END DepartmentDaoTest");
		dao = null;	
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01selectDepartmentByAll() throws SQLException {
		LogUtil.prnLog("==> selectDepartmentByAll()");
		List<Department> list = dao.selectDepartmentByAll();
		LogUtil.prnLog(list.toString());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test02insertDepartment() {
		LogUtil.prnLog("==> insertDepartment()");
		Department newDept = new Department("D006", "연구", 8);
		try {
			int res = dao.insertDepartment(newDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
			if(e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 부서입니다.");
			}
		}	
	}
	
	@Test
	public void test04deleteDepartment() throws SQLException {
		LogUtil.prnLog("==> deleteDepartment()");
		Department delDept = new Department("D006");
		try {
			int res = dao.deleteDepartment(delDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		test01selectDepartmentByAll();
	}
	
	@Test
	public void test03updateDepartment() throws SQLException {
		LogUtil.prnLog("==> updateDepartment()");
		Department updateDept = new Department("D006", "회계", 3);
		try {
			int res = dao.updateDepartment(updateDept);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test01selectDepartmentByAll();
	}
	
	@Test
	public void test05nextNo() {
		try {
			String nextNo = dao.nextDeptNo();
			LogUtil.prnLog("nextNo :" + nextNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

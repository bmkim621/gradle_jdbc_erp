package gradle_jdbc_erp;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mysql.fabric.xmlrpc.base.Data;

import gradle_jdbc_erp.dao.EmployeeDao;
import gradle_jdbc_erp.dao.EmployeeDaoImpl;
import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.dto.Employee;
import gradle_jdbc_erp.dto.Gender;
import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	static EmployeeDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("START EmployeeDaoTest");
		dao = new EmployeeDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("END EmployeeDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01selectEmployeeByAll() throws SQLException {
		LogUtil.prnLog("==> selectEmployeeByAll()");
		List<Employee> list = dao.selectEmployeeByAll();
		LogUtil.prnLog(list.toString());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test02insertEmployeeByAll() throws SQLException {
		LogUtil.prnLog("==> insertEmployeeByAll()");
		Date date = new Date();
		Employee newEmp = new Employee("E017002", "나부장", new Title("T002"), 4000000, Gender.FEMALE, new Department("D001"), date);
		try {
			int res = dao.insertEmployee(newEmp);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
			if(e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 사원입니다.");
			}
		}
	}

}

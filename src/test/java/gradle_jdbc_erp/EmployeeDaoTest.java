package gradle_jdbc_erp;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import gradle_jdbc_erp.dao.EmployeeDao;
import gradle_jdbc_erp.dao.EmployeeDaoImpl;
import gradle_jdbc_erp.dto.Employee;
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

}

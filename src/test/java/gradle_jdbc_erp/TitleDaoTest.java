package gradle_jdbc_erp;

import static org.junit.Assert.*;

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

import gradle_jdbc_erp.dao.DepartmentDaoImpl;
import gradle_jdbc_erp.dao.TitleDao;
import gradle_jdbc_erp.dao.TitleDaoImpl;
import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("START TitleDaoTest");
		dao = new TitleDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("END TitleDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}

	@Test
	public void test01selectTitleByAll() throws SQLException {
		LogUtil.prnLog("==> selectTitleByAll()");
		List<Title> list = dao.selectTitleByAll();
		LogUtil.prnLog(list.toString());
		Assert.assertNotNull(list);
	}
	
	@Test
	public void test02insertTitle() throws SQLException {
		LogUtil.prnLog("==> insertTitle()");
		Title newTitle = new Title("T006", "인턴");
		
		try {
			int res = dao.insertTitle(newTitle);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
			if(e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 직책입니다.");
			}
		}
		test01selectTitleByAll();
	}

	@Test
	public void test04deleteTitle() throws SQLException {
		LogUtil.prnLog("==> deleteTitle()");
		Title delTitle = new Title("T006");
		
		try {
			int res = dao.deleteTitle(delTitle);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			LogUtil.prnLog(e);
		}
		test01selectTitleByAll();
	}
	
	@Test
	public void test03updateTitle() throws SQLException {
		LogUtil.prnLog("==> updateTitle()");
		Title updateTitle = new Title("T006", "신입");
		
		try {
			int res = dao.updateTitle(updateTitle);
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		test01selectTitleByAll();
	}
}

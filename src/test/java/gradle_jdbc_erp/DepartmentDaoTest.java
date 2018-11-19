package gradle_jdbc_erp;

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

	//Select(전체)
	@Test
	public void test01SelectDepartmentByAll() {
		LogUtil.prnLog("SelectDepartmentByAll()");
		
		List<Department> list = dao.selectDepartmentByAll();
		LogUtil.prnLog(list.toString());
		Assert.assertNotNull(list);
	}
	
	//Insert
	@Test
	public void test02InsertDepartment() {
		LogUtil.prnLog("InsertDepartment()");
		//insert 테스트
		Department newDept = new Department("D006", "연구", 12);
		try {
			int res = dao.insertDepartment(newDept);
			//정상적으로 추가됐을 경우 : 1, res = 1이면 추가됐음을 의미
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			//Dupulicate(중복) => 에러코드 1062
//			System.out.println(e.getErrorCode());
			e.printStackTrace();
			if(e.getErrorCode() == 1062) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 부서입니다.");
			}
		}
	}

	
	//Update
	@Test
	public void test03UpdateDepartment() {
		LogUtil.prnLog("UpdateDepartment()");
		Department updateDept = new Department("D006", "회계", 5);
		try {
			int res = dao.updateDepartment(updateDept);
			//정상적으로 수정됐을 경우 : 1, res = 1이면 수정됐음을 의미
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//수정하고 난 뒤 확인하기 위해 다시 전체를 select한다.
		test01SelectDepartmentByAll();
	}
	
	
	//Delete
	@Test
	public void test04DeleteDepartment() {
		LogUtil.prnLog("DeleteDepartment()");
		//부서번호가 D006를 삭제
		Department deleteDept = new Department("D006");
		try {
			int res = dao.deleteDepartment(deleteDept);
			//정상적으로 삭제됐을 경우 : 1, res = 1이면 삭제됐음을 의미
			Assert.assertEquals(1, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//삭제하고 난 뒤 확인하기 위해 다시 전체를 select한다.
		test01SelectDepartmentByAll();
	}
}

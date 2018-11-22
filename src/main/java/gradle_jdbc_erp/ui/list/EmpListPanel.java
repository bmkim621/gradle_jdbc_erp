package gradle_jdbc_erp.ui.list;

import java.util.Date;

import javax.swing.SwingConstants;

import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.dto.Employee;
import gradle_jdbc_erp.dto.Gender;
import gradle_jdbc_erp.dto.Title;

@SuppressWarnings("serial")
public class EmpListPanel extends AbstractListPanel<Employee> {

	@Override
	protected void setAlignWidth() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(180, 150, 100, 150, 100, 150, 180);

	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}

	@Override
	protected Object[] getItemRows(Employee item) {
		
		return new Object[] {
				item.getEmpNo(),
				item.getEmpName(),
				item.getTitleNo().getTitleName(),
				String.format("%,d", item.getSalary()),
				item.getGender() == Gender.FEMALE ? "여자" : "남자",
				String.format("%s(%d층)" , item.getDeptNo().getDeptName(), item.getDeptNo().getFloor()),
				item.getJoinDate()
		};
	}
	
	@Override
	protected Employee getItem(int selectedIndex) {
		String empNo = (String) table.getValueAt(selectedIndex, 0);	//사원번호
		String empName = (String) table.getValueAt(selectedIndex, 1);	//사원명
		Title titleNo = (Title) table.getValueAt(selectedIndex, 2);	//사원번호
		int salary = (int) table.getValueAt(selectedIndex, 3);	//급여
		Gender gender = (Gender) table.getValueAt(selectedIndex, 4);	//성별
		Department deptNo = (Department) table.getValueAt(selectedIndex, 5);	//부서
		Date joinDate = (Date) table.getValueAt(selectedIndex, 6);	//입사일
		
		return new Employee(empNo, empName, titleNo, salary, gender, deptNo, joinDate);
	}



}

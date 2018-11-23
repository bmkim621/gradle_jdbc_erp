package gradle_jdbc_erp.ui.list;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JOptionPane;
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
//		JOptionPane.showMessageDialog(null, "getItem 수정");
		/* employee 테이블은 속성이 titleno, deptno인데 테이블에서는 "사장", "경영"으로 표현되어 있기 때문에 
		사원번호를 검색해서 나머지 데이터를 가져오도록 한다. dto에 있는 Employee에 Generate hashcode()~~~ 에서 empNo에 체크되어있어야 한다. */
		String empNo = (String) table.getValueAt(selectedIndex, 0);	//사원번호
		Employee searchEmp = new Employee(empNo);
		int index = list.indexOf(searchEmp);	//선택되어 있는 데이터의 인덱스를 알아온다.
		return list.get(index);					//인덱스에 해당하는 list를 가지고온다.
		
		
		// ======== 사용할 수 없음 ========> (employee 테이블은 속성이 titleno, deptno인데 테이블에서는 "사장", "경영"으로 표현되어 있기 때문에)
		/*String empName = (String) table.getValueAt(selectedIndex, 1);	//사원명
//		Title titleNo = (Title) table.getValueAt(selectedIndex, 2);	//사원번호
		Title titleNo = new Title((String) table.getValueAt(selectedIndex, 2));	//사원번호
		DecimalFormat df = new DecimalFormat("#,###");
		int salary = 0;
		try {
			salary = df.parse(((String)table.getValueAt(selectedIndex, 3))).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		int salary = Integer.parseInt((String) table.getValueAt(selectedIndex, 3));	//급여
//		Gender gender = (Gender) table.getValueAt(selectedIndex, 4);	//성별
		Gender gender = table.getValueAt(selectedIndex, 4).equals("남자")?Gender.MALE:Gender.FEMALE;	//성별
//		Department deptNo = (Department) table.getValueAt(selectedIndex, 5);	//부서
		Department deptNo = new Department( (String) table.getValueAt(selectedIndex, 5));	//부서
		Date joinDate = (Date) table.getValueAt(selectedIndex, 6);	//입사일
		
		return new Employee(empNo, empName, titleNo, salary, gender, deptNo, joinDate);*/
	}
}

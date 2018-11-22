package gradle_jdbc_erp.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_erp.dto.Department;

@SuppressWarnings("serial")
public class DeptListPanel extends AbstractListPanel<Department> {
	
	
	protected void setAlignWidth() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableSetWidth(150, 150, 150);
		
	}
	
	@Override
	protected String[] getColumnNames() {
		return new String[] {"번호", "부서명", "위치"};
	}

	@Override
	protected Object[] getItemRows(Department dept) {
		return new Object[] {dept.getDeptNo(), dept.getDeptName(), dept.getFloor()};
	}
	
	@Override
	protected Department getItem(int selectedIndex) {
		String deptNo = (String) table.getValueAt(selectedIndex, 0);	//부서번호
		String deptName = (String) table.getValueAt(selectedIndex, 1); //부서명
		int floor = (int) table.getValueAt(selectedIndex, 2);	//위치
//		System.out.println(getDeptName);
		return new Department(deptNo, deptName, floor);
	}

}

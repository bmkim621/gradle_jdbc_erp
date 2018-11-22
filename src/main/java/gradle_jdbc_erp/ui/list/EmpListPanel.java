package gradle_jdbc_erp.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_erp.dto.Employee;

@SuppressWarnings("serial")
public class EmpListPanel extends AbstractListPanel<Employee> {

	@Override
	protected void setAlignWidth() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(150, 150, 100, 200, 100, 150, 150);

	}

	@Override
	protected String[] getColumnNames() {
		return new String[] {"번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}

	@Override
	protected Object[] getItemRows(Employee item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Employee getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Employee getItem(int selectedIndex) {
		// TODO Auto-generated method stub
		return null;
	}



}

package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gradle_jdbc_erp.dto.Department;

public class DeptListPanel extends JPanel {
	private JTable table;
	
	//리스트
	private List<Department> list;
	
	public void setList(List<Department> list) {
		this.list = list;
	}

	/**
	 * Create the panel.
	 */
	public DeptListPanel() {
		initcomponents();
	}

	private void initcomponents() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	//데이터 불러오기
	public void loadDatas() {
		table.setModel(new MyTableModel(getDatas(), getColumnNames()));
	}

	private String[] getColumnNames() {
		return new String[] {"번호", "부서명", "위치"};
	}

	private Object[][] getDatas() {
		Object[][] datas = new Object[list.size()][];
		for(int i = 0 ; i < list.size() ; i++) {
			datas[i] = getDeptRows(list.get(i));
		}
		return datas;
	}

	private Object[] getDeptRows(Department dept) {
		return new Object[] {dept.getDeptNo(), dept.getDeptName(), dept.getFloor()};
	}
	
	

}

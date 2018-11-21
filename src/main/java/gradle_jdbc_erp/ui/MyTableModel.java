package gradle_jdbc_erp.ui;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	public MyTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

}

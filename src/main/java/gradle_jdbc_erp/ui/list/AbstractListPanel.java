package gradle_jdbc_erp.ui.list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import gradle_jdbc_erp.dto.Department;

@SuppressWarnings("serial")
public abstract class AbstractListPanel<T> extends JPanel {
	protected JTable table;
	
	//리스트
	protected List<T> list;

	protected JScrollPane scrollPane;
	
	public void setList(List<T> list) {
		this.list = list;
	}

	public AbstractListPanel() {
		initcomponents();
	}

	private void initcomponents() {
		setLayout(new BorderLayout(0, 0));
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
	}
	
	protected abstract void setAlignWidth();
	
	//정렬
	protected void tableCellAlignment(int align, int...idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < idx.length ; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	//너비
	protected void tableSetWidth(int...width) {
		TableColumnModel tcm = table.getColumnModel();
		for(int i = 0 ; i < width.length ; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	//데이터 불러오기
	public void loadDatas() {
		table.setModel(new MyTableModel(getDatas(), getColumnNames()));
		setAlignWidth();	//table.setModel 다음에 데이터가 들어오기 때문에 그 다음에 setAlignWidth() 호출해야 함.
	}

	protected abstract String[] getColumnNames();

	private Object[][] getDatas() {
		Object[][] datas = new Object[list.size()][];
		for(int i = 0 ; i < list.size() ; i++) {
			datas[i] = getItemRows(list.get(i));
		}
		return datas;
	}

	protected abstract Object[] getItemRows(T item);
	
	//테이블에 팝업메뉴 달기
	public void setPopupMenu(JPopupMenu createDeptPopupMenu) {
		//scrollpane 영역에서도 팝업메뉴 나올 수 있도록
		scrollPane.setComponentPopupMenu(createDeptPopupMenu);
		table.setComponentPopupMenu(createDeptPopupMenu);
	}
	
	public T getSelectedItem() {
		//선택된 데이터의 인덱스 가지고오기
		int selectedIndex = table.getSelectedRow();
		if(selectedIndex == -1) {
			JOptionPane.showMessageDialog(null, "다시 선택해주세요.");
		}
//		System.out.println(selectedIndex);
		//선택된 데이터의 인덱스 column값 가지고오기
		return getItem(selectedIndex);
	}

	protected abstract T getItem(int selectedIndex);
	
	//내부클래스
	class MyTableModel extends DefaultTableModel {

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
}

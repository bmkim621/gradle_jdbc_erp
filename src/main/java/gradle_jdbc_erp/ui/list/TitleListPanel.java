package gradle_jdbc_erp.ui.list;

import javax.swing.SwingConstants;

import gradle_jdbc_erp.dto.Title;

@SuppressWarnings("serial")
public class TitleListPanel extends AbstractListPanel<Title> {
	
	
	protected void setAlignWidth() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1);
		tableSetWidth(150, 150);
	}
	
	@Override
	protected String[] getColumnNames() {
		return new String[] {"번호", "직책"};
	}
	
	@Override
	protected Object[] getItemRows(Title title) {
		return new Object[] {title.getTitleNo(), title.getTitleName()};
	}

	@Override
	protected Title getItem(int selectedIndex) {
		String titleNo = (String) table.getValueAt(selectedIndex, 0);
		String titleName = (String) table.getValueAt(selectedIndex, 1);
		
		return new Title(titleNo, titleName);
	}

}

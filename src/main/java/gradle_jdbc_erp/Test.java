package gradle_jdbc_erp;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.service.TitleUIServce;

public class Test {

	public static void main(String[] args) throws SQLException {
/*		String dno = "D012";
		String no = dno.substring(1);
		System.out.println(no);
		int dnum = Integer.parseInt(no) + 1;
		System.out.println(dnum);
		System.out.println(String.format("D%03d", dnum));*/
		
		//현재날짜
		String current = LocalDate.now().getYear() + "";
	/*	String dno = "D012";
		String no = dno.substring(1);
		int dnum = Integer.parseInt(no) + 1;
		String year = (String) current;*/
		String no1 = (String) current.substring(1);

		TitleUIServce service = new TitleUIServce();
		List<Title> list = service.selectAll();
		
		DefaultComboBoxModel<Title> model = new DefaultComboBoxModel<>(new Vector<>(list));
		JComboBox<Title> cmb = new JComboBox<>(model);
		
		String[] strArr3 = {"apple", "banana", "cherry", "kiwi", "mango", "pear"};
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(strArr3);
		JComboBox<String> cmb3 = new JComboBox<>(model1);
		JButton btn = new JButton("확인");
		btn.addActionListener(e->{
			int selectedIndex = cmb.getSelectedIndex();
			Title selectedTitle = (Title) cmb.getSelectedItem();
			JOptionPane.showMessageDialog(null, "Index : " + selectedIndex + "Title : " + selectedTitle.getTitleNo() + "," + selectedTitle.getTitleName());
		});
		cmb.setSelectedItem(new Title("T004", "과장"));
//		cmb.setSelectedIndex(3);
		JFrame jf = new JFrame();
		jf.setSize(200, 150);
		jf.add(cmb3, BorderLayout.NORTH);
		jf.add(cmb, BorderLayout.CENTER);
		jf.add(btn, BorderLayout.SOUTH);
		jf.setVisible(true);
		
	}

}

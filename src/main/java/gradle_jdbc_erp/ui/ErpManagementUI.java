package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDept;
	private JButton btnDuty;
	private JButton btnEmp;

	/**
	 * Create the frame.
	 */
	public ErpManagementUI() {
		setTitle("ERP관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 0));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		btnEmp.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		btnDept.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(btnDept);
		
		btnDuty = new JButton("직책관리");
		btnDuty.addActionListener(this);
		btnDuty.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		contentPane.add(btnDuty);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmp) {
			do_btnEmp_actionPerformed(e);
		}
		if (e.getSource() == btnDuty) {
			do_btnDuty_actionPerformed(e);
		}
		if (e.getSource() == btnDept) {
			do_btnDept_actionPerformed(e);
		}
	}
	
	//부서관리 버튼
	protected void do_btnDept_actionPerformed(ActionEvent e) {
		DeptManagementUI ui = new DeptManagementUI();
		ui.setVisible(true);
	}
	
	//직책관리 버튼
	protected void do_btnDuty_actionPerformed(ActionEvent e) {
		TitleManagementUI ui = new TitleManagementUI();
		ui.setVisible(true);
	}
	
	protected void do_btnEmp_actionPerformed(ActionEvent e) {
		EmpManagementUI ui = new EmpManagementUI();
		ui.setVisible(true);
	}
}

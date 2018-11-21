package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_erp.service.DeptUIService;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.awt.Font;

public class DeptManagementUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private DeptUIService service;
	/**
	 * Create the frame.
	 */
	public DeptManagementUI() {
		service = new DeptUIService();
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 20));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblDeptNo = new JLabel("번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeptNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel.add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		tfDeptNo.setEnabled(false);
		panel.add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JLabel lblDeptName = new JLabel("부서명");
		lblDeptName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDeptName);
		
		tfDeptName = new JTextField();
		panel.add(tfDeptName);
		tfDeptName.setColumns(10);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFloor.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel.add(lblFloor);
		
		tfFloor = new JTextField();
		panel.add(tfFloor);
		tfFloor.setColumns(10);
		
		JPanel pButton1 = new JPanel();
		panel.add(pButton1);
		pButton1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("");
		pButton1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		pButton1.add(lblNewLabel_4);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pButton1.add(btnAdd);
		
		JPanel pButton2 = new JPanel();
		panel.add(pButton2);
		pButton2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pButton2.add(btnCancel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		pButton2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		pButton2.add(lblNewLabel_6);
		
		DeptListPanel pTable = new DeptListPanel();
		try {
			pTable.setList(service.selectAll());
			pTable.loadDatas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(pTable, BorderLayout.CENTER);
	}

}

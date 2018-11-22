package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_erp.ui.list.EmpListPanel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class EmpManagementUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfJoinDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpManagementUI frame = new EmpManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmpManagementUI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 20));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 4, 10, 10));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel lblEmpNo = new JLabel("번호");
		lblEmpNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		tfEmpNo.setEditable(false);
		panel.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		JLabel lblEmpName = new JLabel("사원명");
		lblEmpName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblEmpName);
		
		tfEmpName = new JTextField();
		panel.add(tfEmpName);
		tfEmpName.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		panel.add(lblNewLabel_6);
		
		JLabel lblTitleNo = new JLabel("직책");
		lblTitleNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleNo);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		JLabel lblNewLabel_8 = new JLabel("");
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		panel.add(lblNewLabel_9);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblSalary);
		
		JSpinner spinner = new JSpinner();
		panel.add(spinner);
		
		JLabel lblNewLabel_11 = new JLabel("");
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		panel.add(lblNewLabel_12);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblGender);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 10, 0));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("남");
		rdbtnNewRadioButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("여");
		rdbtnNewRadioButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_14 = new JLabel("");
		panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("");
		panel.add(lblNewLabel_15);
		
		JLabel lblDeptNo = new JLabel("부서");
		lblDeptNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblDeptNo);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_17 = new JLabel("");
		panel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("");
		panel.add(lblNewLabel_18);
		
		JLabel lblJoinDate = new JLabel("입사일");
		lblJoinDate.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblJoinDate.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblJoinDate);
		
		tfJoinDate = new JTextField();
		panel.add(tfJoinDate);
		tfJoinDate.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("");
		panel.add(lblNewLabel_20);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_21 = new JLabel("");
		panel_3.add(lblNewLabel_21);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_3.add(btnAdd);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnCancel = new JButton("삭제");
		btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_4.add(btnCancel);
		
		JLabel lblNewLabel_22 = new JLabel("");
		panel_4.add(lblNewLabel_22);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		EmpListPanel pTable = new EmpListPanel();
		contentPane.add(pTable, BorderLayout.CENTER);
	}

}

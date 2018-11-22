package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import gradle_jdbc_erp.service.EmployeeUIService;
import gradle_jdbc_erp.ui.list.EmpListPanel;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfJoinDate;
	private EmpListPanel pTable;
	private EmployeeUIService empService;

	//라디오버튼 하나만 선택되려면 그룹으로 묶어야 함
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JFormattedTextField currentDate;
	
	public void setpTable(EmpListPanel pTable) {
		this.pTable = pTable;
	}
	
	/**
	 * Create the frame.
	 */
	public EmpManagementUI() {
		setResizable(false);
		empService = new EmployeeUIService();
		initComponents();
	}
	private void initComponents() {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 533);
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
		lblEmpNo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblEmpNo);
		
		//사원번호
		tfEmpNo = new JTextField();
		tfEmpNo.setEditable(false);
		try {
			tfEmpNo.setText(empService.nextEmpNo());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		JLabel lblEmpName = new JLabel("사원명");
		lblEmpName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblEmpName.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblTitleNo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTitleNo);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		JLabel lblNewLabel_8 = new JLabel("");
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		panel.add(lblNewLabel_9);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSalary);
		
		JSpinner spinnerSalary = new JSpinner();
													//기본값, 최소값, 최대값, stepSize
		spinnerSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		panel.add(spinnerSalary);
		
		JLabel lblNewLabel_11 = new JLabel("");
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		panel.add(lblNewLabel_12);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblGender);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 10, 0));
		
		rdbtnMale = new JRadioButton("남");
		rdbtnMale.setSelected(true);	//남자가 선택되어짐
		buttonGroup.add(rdbtnMale); //라디오 버튼 그룹
		rdbtnMale.addActionListener(this);
		rdbtnMale.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("여");
		rdbtnFemale.addActionListener(this);
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(rdbtnFemale);
		
		JLabel lblNewLabel_14 = new JLabel("");
		panel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("");
		panel.add(lblNewLabel_15);
		
		JLabel lblDeptNo = new JLabel("부서");
		lblDeptNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblDeptNo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDeptNo);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_17 = new JLabel("");
		panel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("");
		panel.add(lblNewLabel_18);
		
		JLabel lblJoinDate = new JLabel("입사일");
		lblJoinDate.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblJoinDate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblJoinDate);
		
		//입사일
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
		
		pTable = new EmpListPanel();
		try {
			pTable.setList(empService.selectAll());
			pTable.loadDatas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(pTable, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == rdbtnFemale) {
			do_rdbtnFemale_actionPerformed(arg0);
		}
		if (arg0.getSource() == rdbtnMale) {
			do_rdbtnMale_actionPerformed(arg0);
		}
	}
	
	//남자
	protected void do_rdbtnMale_actionPerformed(ActionEvent arg0) {
	}
	
	//여자
	protected void do_rdbtnFemale_actionPerformed(ActionEvent arg0) {
	}
}

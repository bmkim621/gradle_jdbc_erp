package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.dto.Employee;
import gradle_jdbc_erp.dto.Gender;
import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.service.EmployeeUIService;
import gradle_jdbc_erp.ui.list.EmpListPanel;

@SuppressWarnings("serial")
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
	private JComboBox<Title> cmbTitle;
	private JComboBox<Department> cmbDept;
	private JButton btnAdd;
	private JSpinner spinnerSalary;
	private JButton btnCancel;
	
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
		
		//직책 콤보박스
		try {
			DefaultComboBoxModel<Title> titleModel = new DefaultComboBoxModel<>(new Vector<>(empService.selectTitles()));
			cmbTitle = new JComboBox<>(titleModel);	
			cmbTitle.setSelectedIndex(-1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel.add(cmbTitle);
		
		JLabel lblNewLabel_8 = new JLabel("");
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		panel.add(lblNewLabel_9);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblSalary.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSalary);
		
		spinnerSalary = new JSpinner();
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
		buttonGroup.add(rdbtnMale); //라디오 버튼 그룹(하나만 선택하기 위해서)
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
		
		//직책 콤보박스
		try {
			DefaultComboBoxModel<Department> deptModel = new DefaultComboBoxModel<>(new Vector<>(empService.selectDepts()));
			cmbDept = new JComboBox<>(deptModel);
			cmbDept.setSelectedIndex(-1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel.add(cmbDept);
		
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
		tfJoinDate.setText(String.format("%tF", new Date()));
		
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
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_3.add(btnAdd);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_4.add(btnCancel);
		
		JLabel lblNewLabel_22 = new JLabel("");
		panel_4.add(lblNewLabel_22);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		//테이블
		pTable = new EmpListPanel();
		try {
			pTable.setList(empService.selectAll());
			pTable.loadDatas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(pTable, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("취소")) {
			clearTf();
		}
		if (e.getActionCommand().equals("추가")) {
			do_btnAdd_actionPerformed(e);
		}
	}
	
	//추가
	protected void do_btnAdd_actionPerformed(ActionEvent arg0) {
		
		try {
			vaildCheck();
			
			Employee emp = getEmployee();
			int res = empService.addEmp(emp);

			if(res == 1) {
				JOptionPane.showMessageDialog(null, "추가했습니다.");
				pTable.setList(empService.selectAll());
				pTable.loadDatas();
			}
			clearTf();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {	//사원명이 비어있는 경우
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	//검사
	private void vaildCheck() throws Exception {
		//사원명이 비어있는 경우
		if(tfEmpName.getText().equals("")) {
			tfEmpName.requestFocus();
			throw new Exception("사원명을 입력해주세요.");	//예외를 만든다.
		}
		//직책이 선택되어있지 않은 경우
		if(cmbTitle.getSelectedIndex() == -1) {
			throw new Exception("직책을 선택해주세요.");
		}
		if(cmbDept.getSelectedIndex() == -1) {
			throw new Exception("부서를 선택해주세요.");
		}
		
	}

	//텍스트 필드 초기화하기
	private void clearTf() {
		try {
			tfEmpNo.setText(empService.nextEmpNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tfEmpName.setText("");
		cmbTitle.setSelectedIndex(-1);
		spinnerSalary.setValue(1500000);
		rdbtnMale.setSelected(true);
		cmbDept.setSelectedIndex(-1);
		tfJoinDate.setText(String.format("%tF", new Date()));
		tfEmpName.requestFocus();
		
	}

	//입력한 값 가지고 오기
	private Employee getEmployee() {
		String empNo = tfEmpNo.getText().trim();	//사원번호
		String empName = tfEmpName.getText().trim();	//사원명
		Title titleNo = (Title) cmbTitle.getSelectedItem();
		int salary = (int) spinnerSalary.getValue();
		Gender gender = null;
		if(rdbtnFemale.isSelected()) {
			gender = Gender.FEMALE;
		} else {
			gender = Gender.MALE;
		}
		Department deptNo = (Department) cmbDept.getSelectedItem();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date joinDate = null;
		try {
			joinDate = sdf.parse(tfJoinDate.getText().trim());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Employee(empNo, empName, titleNo, salary, gender, deptNo, joinDate);
		
	}
	
	
}

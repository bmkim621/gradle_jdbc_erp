package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_erp.dao.DepartmentDao;
import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.service.DeptUIService;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeptManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private DeptUIService service;
	private JButton btnAdd;
/*	
	private DepartmentDao dao;
	
	public void setDao(DepartmentDao dao) {
		this.dao = dao;
	}*/

	//DeptListPanel을 사용할 수 있도록 필드로 정의
	private DeptListPanel pTable;
	
	//DeptListPanel의 참조주소를 넘겨준다. getters/setters
	public void setpTable(DeptListPanel pTable) {
		this.pTable = pTable;
	}
	
	/**
	 * Create the frame.
	 */
	public DeptManagementUI() {
		service = new DeptUIService();
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		try {
			tfDeptNo.setText(service.nextDeptNo());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
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
		
		pTable = new DeptListPanel();
		try {
			pTable.setList(service.selectAll());
			pTable.loadDatas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(pTable, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			do_btnAdd_actionPerformed(e);
		}
	}
	
	//추가 => Department 테이블에 insert
	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		Department dept = getDepartment();
		int res;
		try {
			res = service.addDept(dept);
			if(res == 1) {	//1 : 정상적으로 추가
				JOptionPane.showMessageDialog(null, "추가했습니다.");
				pTable.loadDatas();
			}
			clearTf();	//추가 후 텍스트필드 초기화
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

	//텍스트 필드 초기화하기
	private void clearTf() {
//		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfFloor.setText("");	
	}

	//텍스트 필드에 입력한 값 가지고 오기
	private Department getDepartment() {
		String deptNo = tfDeptNo.getText().trim();
		String deptName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		
		return new Department(deptNo, deptName, floor);
	}
}

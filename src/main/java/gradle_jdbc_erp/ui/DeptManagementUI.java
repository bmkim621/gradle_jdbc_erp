package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_erp.dto.Department;
import gradle_jdbc_erp.jdbc.LogUtil;
import gradle_jdbc_erp.service.DeptUIService;
import gradle_jdbc_erp.ui.list.AbstractListPanel;
import gradle_jdbc_erp.ui.list.DeptListPanel;

@SuppressWarnings("serial")
public class DeptManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private DeptUIService service;
	private JButton btnAdd;
	
	private AbstractListPanel<Department> pTable;
	private JButton btnCancel;
	
	public void setpTable(DeptListPanel pTable) {
		this.pTable = pTable;
	}
	
	/**
	 * Create the frame.
	 */
	public DeptManagementUI() {
		service = new DeptUIService();
		initComponents();
	}
	private void initComponents() {
		setResizable(false);
		
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
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
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
			pTable.setPopupMenu(createDeptPopupMenu());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(pTable, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("추가")) {
			do_btnAdd_actionPerformed(e);
		}
		if (e.getActionCommand().equals("수정")) {
			do_btnUpdate_actionPerformed(e);
		}
		if (e.getActionCommand().equals("취소")) {
			dispose();
		}
/*		if (e.getSource() == btnCancel) {
			dispose();
		}
		if (e.getSource() == btnAdd) {
			if(btnAdd.getText().equals("추가")) {
				do_btnAdd_actionPerformed(e);
			}
			else{
				
			}
		}*/
	}
	
	//수정버튼
	public void do_btnUpdate_actionPerformed(ActionEvent e) {
		Department dept = getDepartment();
		int res;
		try {
			res = service.updateDept(dept);
			if(res == 1) {
				JOptionPane.showMessageDialog(null, "수정했습니다.");
				pTable.setList(service.selectAll());
				pTable.loadDatas();
			}
			clearTf();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		btnAdd.setText("추가");
	}
	
	//추가 => Department 테이블에 insert
	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		Department dept = getDepartment();
		int res;
		try {
			res = service.addDept(dept);
			if(res == 1) {	//1 : 정상적으로 추가
				JOptionPane.showMessageDialog(null, "추가했습니다.");
				pTable.setList(service.selectAll());
				pTable.loadDatas();
			}
			clearTf();	//추가 후 텍스트필드 초기화
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	//텍스트 필드 초기화하기
	private void clearTf() {
		try {
			tfDeptNo.setText(service.nextDeptNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	//새로운 값으로 바꾸기
	private void setDepartment(Department dept) {
		tfDeptNo.setText(dept.getDeptNo());
		tfDeptName.setText(dept.getDeptName());
		tfFloor.setText(dept.getFloor()+"");
	}
	
	//팝업메뉴
	private JPopupMenu createDeptPopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		//수정
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(e->{
			Department selectedDept = pTable.getSelectedItem();
			setDepartment(selectedDept);
			btnAdd.setText("수정");
		});
/*		updateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Department selectedDept = pTable.getSelectedDept();
				setDepartment(selectedDept);
				btnAdd.setText("수정");
//확인			JOptionPane.showMessageDialog(null, selectedDept);
			}
		});*/
		popupMenu.add(updateItem);
		
		JMenuItem delItem = new JMenuItem("삭제");
		delItem.addActionListener(e->{
			Department selectedDept = pTable.getSelectedItem();
			try {
				service.deleteDept(selectedDept);
				pTable.setList(service.selectAll());
				pTable.loadDatas();
			} catch (SQLException e1) {
				if(e1.getErrorCode() == 1451) { //errorcode 1451 : 참조 무결성
					JOptionPane.showMessageDialog(null, "해당부서에 소속된 사원이 존재합니다.");
				} else {
					LogUtil.prnLog(e1);
					e1.printStackTrace();
				}
			}
		});
		
		popupMenu.add(delItem);
		return popupMenu;
	}
}

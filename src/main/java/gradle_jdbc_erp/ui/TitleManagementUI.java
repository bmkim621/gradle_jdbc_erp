package gradle_jdbc_erp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_erp.dto.Title;
import gradle_jdbc_erp.service.TitleUIServce;
import gradle_jdbc_erp.ui.list.TitleListPanel;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TitleManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfTitleNo;
	private JTextField tfTitleName;
	private TitleUIServce titleService;
	private TitleListPanel tPanel;
	private JButton btnAdd;
	private JButton btnCancel;
	
	public void settPanel(TitleListPanel tPanel) {
		this.tPanel = tPanel;
	}
	
	/**
	 * Create the frame.
	 */
	public TitleManagementUI() {
		titleService = new TitleUIServce(); //서비스 사용하려면
		initComponents();
	}
	private void initComponents() {
		setResizable(false);
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 390, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 20));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblTitleNo = new JLabel("번호");
		lblTitleNo.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblTitleNo.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleNo);
		
		tfTitleNo = new JTextField();
		tfTitleNo.setEditable(false);
		try {
			tfTitleNo.setText(titleService.nextTitleNo());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		panel.add(tfTitleNo);
		tfTitleNo.setColumns(10);
		
		JLabel lblTitleName = new JLabel("직책명");
		lblTitleName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblTitleName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTitleName);
		
		tfTitleName = new JTextField();
		panel.add(tfTitleName);
		tfTitleName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_1.add(lblNewLabel_3);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btnAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(btnAdd);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_2.add(btnCancel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel_2.add(lblNewLabel_5);
		
		JLabel label = new JLabel("");
		panel_2.add(label);
	
		
		tPanel = new TitleListPanel();
		try {
			tPanel.setList(titleService.selectAll());
			tPanel.loadDatas();
			tPanel.setPopupMenu(createTitlePopupMenu());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		contentPane.add(tPanel, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("취소")) {
			dispose();
		}
		if (e.getActionCommand().equals("추가")) {
			do_btnAdd_actionPerformed(e);
		}
		if (e.getActionCommand().equals("수정")) {
			do_btnUpdate_actionPerformed(e);
		}
		
	}
	
	//팝업메뉴
	private JPopupMenu createTitlePopupMenu() {
		JPopupMenu popupMenu = new JPopupMenu();
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(e->{
			Title selectedTitle = tPanel.getSelectedItem();
			setTitle(selectedTitle);
			btnAdd.setText("수정");
		});
		popupMenu.add(updateItem);
		
		JMenuItem delItem = new JMenuItem("삭제");
		delItem.addActionListener(e->{
			Title selectedTitle = tPanel.getSelectedItem();
			try {
				titleService.deleteTitle(selectedTitle);
				tPanel.setList(titleService.selectAll());
				tPanel.loadDatas();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		popupMenu.add(delItem);
		
		return popupMenu;
	}
	
	//추가
	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		Title title = getTitles();
		int res;
		try {
			res = titleService.addTitle(title);
			if(res == 1) {
				JOptionPane.showMessageDialog(null, "추가했습니다.");
				tPanel.setList(titleService.selectAll());
				tPanel.loadDatas();
			}
			clearTf();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	//수정
	private void do_btnUpdate_actionPerformed(ActionEvent e) {
		Title title = getTitles();
		int res;
		try {
			res = titleService.updateTitle(title);
			if(res == 1) {
				JOptionPane.showMessageDialog(null, "수정했습니다.");
				tPanel.setList(titleService.selectAll());
				tPanel.loadDatas();
			}
			clearTf();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		btnAdd.setText("추가");
	}
	
	//텍스트 필드 초기화
	private void clearTf() {
		try {
			tfTitleNo.setText(titleService.nextTitleNo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tfTitleName.setText("");
		
	}

	//새로운 값으로 바꾸기
	private void setTitle(Title title) {
		tfTitleNo.setText(title.getTitleNo());
		tfTitleName.setText(title.getTitleName());
	}
		
	private Title getTitles() {
		String titleNo = tfTitleNo.getText().trim();
		String titleName = tfTitleName.getText().trim();
		
		return new Title(titleNo, titleName);
	}

	protected void do_btnCancel_actionPerformed(ActionEvent e) {
	}
}

package gradle_jdbc_erp;

import java.awt.EventQueue;

import gradle_jdbc_erp.ui.DeptManagementUI;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeptManagementUI frame = new DeptManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

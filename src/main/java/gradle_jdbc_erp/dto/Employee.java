package gradle_jdbc_erp.dto;

import java.util.Date;

public class Employee {
	private String empNo;
	private String empName;
	private Title titleNo;
	private int salary;
	private Gender gender;
	private Department deptNo;
	private Date joinDate;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(String empNo, String empName, Title titleNo, int salary, Gender gender, Department deptNo,
			Date joinDate) {
		this.empNo = empNo;
		this.empName = empName;
		this.titleNo = titleNo;
		this.salary = salary;
		this.gender = gender;
		this.deptNo = deptNo;
		this.joinDate = joinDate;
	}

	public Employee(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(Title titleNo) {
		this.titleNo = titleNo;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Department getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Department deptNo) {
		this.deptNo = deptNo;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee [empNo=%s, empName=%s, titleNo=%s, salary=%s, gender=%s, deptNo=%s, joinDate=%s]", empNo,
				empName, titleNo, salary, gender, deptNo, joinDate);
	}
	
	
}

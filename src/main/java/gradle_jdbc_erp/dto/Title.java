package gradle_jdbc_erp.dto;

public class Title {
	private String titleNo;
	private String titleName;
	
	public Title() {
		// TODO Auto-generated constructor stub
	}

	public Title(String titleNo, String titleName) {
		this.titleNo = titleNo;
		this.titleName = titleName;
	}

	public Title(String titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(String titleNo) {
		this.titleNo = titleNo;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return String.format("Title [titleNo=%s, titleName=%s]", titleNo, titleName);
	}
}

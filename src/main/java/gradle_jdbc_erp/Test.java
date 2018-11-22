package gradle_jdbc_erp;

import java.time.LocalDate;

public class Test {

	public static void main(String[] args) {
/*		String dno = "D012";
		String no = dno.substring(1);
		System.out.println(no);
		int dnum = Integer.parseInt(no) + 1;
		System.out.println(dnum);
		System.out.println(String.format("D%03d", dnum));*/
		
		//현재날짜
		String current = LocalDate.now().getYear() + "";
	/*	String dno = "D012";
		String no = dno.substring(1);
		int dnum = Integer.parseInt(no) + 1;
		String year = (String) current;*/
		String no1 = (String) current.substring(1);

		
	}

}

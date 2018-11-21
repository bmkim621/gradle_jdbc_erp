package gradle_jdbc_erp;

public class Test {

	public static void main(String[] args) {
		String dno = "D012";
		String no = dno.substring(1);
		System.out.println(no);
		int dnum = Integer.parseInt(no) + 1;
		System.out.println(dnum);
		System.out.println(String.format("D%03d", dnum));
	}

}

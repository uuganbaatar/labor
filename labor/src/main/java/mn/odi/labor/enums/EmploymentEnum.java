package mn.odi.labor.enums;

public enum EmploymentEnum {

	EMPLOYEE(0), EMPLOYER(1), HUVIARAA(2), HORSHOO(3), MALCHIN(4), URHIIN(5), OTHER(6);

	private final int val;

	EmploymentEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

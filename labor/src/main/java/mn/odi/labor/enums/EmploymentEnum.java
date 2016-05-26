package mn.odi.labor.enums;

public enum EmploymentEnum {

	AJILTAI(0), AJILGUI(1);

	private final int val;

	EmploymentEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

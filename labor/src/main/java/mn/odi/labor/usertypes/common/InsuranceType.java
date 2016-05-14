package mn.odi.labor.usertypes.common;

public enum InsuranceType {

	MINE(0), OFFICE(1);

	private final int val;

	InsuranceType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

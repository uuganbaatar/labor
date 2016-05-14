package mn.odi.labor.usertypes.common;

public enum ReasonLeave {

	ANNUAL(0), SICK(1), MATERNITY(2);

	private final int val;

	ReasonLeave(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

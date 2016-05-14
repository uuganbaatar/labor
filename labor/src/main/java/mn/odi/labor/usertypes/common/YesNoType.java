package mn.odi.labor.usertypes.common;

public enum YesNoType {
	YES(0), NO(1);

	private final int val;

	YesNoType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

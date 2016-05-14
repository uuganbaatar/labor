package mn.odi.labor.usertypes.common;

public enum MineSiteType {

	YES(0), YESOT(1), NO(2);

	private final int val;

	MineSiteType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

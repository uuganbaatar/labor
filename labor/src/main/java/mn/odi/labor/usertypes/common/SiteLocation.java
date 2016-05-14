package mn.odi.labor.usertypes.common;

public enum SiteLocation {

	UB(0), OT(1);

	private final int val;

	SiteLocation(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

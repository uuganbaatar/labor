package mn.odi.labor.usertypes.common;

public enum ShiftSelection {

	NIGHT(0), WEEKEND(1), HOLIDAY(2), REST(3), REGULAR(4);

	private final int val;

	ShiftSelection(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

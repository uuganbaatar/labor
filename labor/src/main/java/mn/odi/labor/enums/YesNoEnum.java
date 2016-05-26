package mn.odi.labor.enums;

public enum YesNoEnum {

	YES(0), NO(1);

	private final int val;

	YesNoEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

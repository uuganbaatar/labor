package mn.odi.labor.enums;

public enum GenderEnum {

	MALE(0), FEMALE(1);

	private final int val;

	GenderEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

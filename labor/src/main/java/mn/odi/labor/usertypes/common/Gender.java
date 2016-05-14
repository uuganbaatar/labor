package mn.odi.labor.usertypes.common;

public enum Gender {

	MALE(0), FEMALE(1);

	private final int val;

	Gender(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

package mn.odi.labor.enums;

public enum RoleEnum {
	ADMIN(0), USER(1), LABORUSER(2);

	private final int val;

	RoleEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

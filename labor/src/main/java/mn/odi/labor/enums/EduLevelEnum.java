package mn.odi.labor.enums;

public enum EduLevelEnum {
	DUND(0), BURENDUND(1), BACHELOR(2), MASTER(3), PHD(4);

	private final int val;

	EduLevelEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

package mn.odi.labor.enums;

public enum PropertyTypeEnum {

	TURIIN(0), ORONNUTGIIN(1), HUVIIN(2);

	private final int val;

	PropertyTypeEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

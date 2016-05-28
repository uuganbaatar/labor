package mn.odi.labor.enums;

public enum EmpMovementEnum {

	NOTCHANGED(0), INNERMOVE(1), OUTERMOVE(2), FIRED(3);

	private final int val;

	EmpMovementEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

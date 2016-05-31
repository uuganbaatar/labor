package mn.odi.labor.enums;

public enum EduLevelEnum {
	BOLOVSROLGUI(0), BAGA(1), SUURI(2), BURENDUND(3), TECHNICAL(4), SPECIAL(5), BACHELOR(6), MASTER(7), PHD(8);

	private final int val;

	EduLevelEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

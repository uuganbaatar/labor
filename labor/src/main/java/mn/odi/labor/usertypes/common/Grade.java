package mn.odi.labor.usertypes.common;

public enum Grade {

	G1(0), G2(1), G3(2), G4(3), G5(4), G6(5), G7(6), G8(7), G9(8), G10(9), G11(
			10), G12(11);

	private final int val;

	Grade(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

}

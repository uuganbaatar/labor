package mn.odi.labor.enums;

public enum ReportDetailType {
	NEMEGDSEN(0), HASAGDSAN(1), DUN(2);

	private final int val;

	ReportDetailType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

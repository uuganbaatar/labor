package mn.odi.labor.enums;

public enum ReportDetailType {
	NEMEGDSEN(0), HASAGDSAN(1);

	private final int val;

	ReportDetailType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

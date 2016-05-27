package mn.odi.labor.enums;

public enum ReportStatusEnum {

	SENT(0), DRAFT(1);

	private final int val;

	ReportStatusEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

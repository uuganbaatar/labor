package mn.odi.labor.enums;

public enum JobTypeEnum {
	PERMANENT(0), QUARTERLY(1), TEMPORARAY(2), PARTTIME(3);

	private final int val;

	JobTypeEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

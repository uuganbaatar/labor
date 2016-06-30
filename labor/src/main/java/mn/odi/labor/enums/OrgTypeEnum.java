package mn.odi.labor.enums;

public enum OrgTypeEnum {

	AJILLOLGOGCH(0), HELTES(1);

	private final int val;

	OrgTypeEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}

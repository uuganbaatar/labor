package mn.odi.labor.entities;

public enum ExecuteCommand {

	DISABLED(0), 
	ENABLED(1),
	CHANGEPASSWORD(2);
	
	private final int val;

	ExecuteCommand(int val) {
		this.val = val;
	}
	public int getVal() {
		// TODO Auto-generated method stub
		return val;
	}
}

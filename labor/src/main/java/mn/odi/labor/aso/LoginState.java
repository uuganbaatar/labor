package mn.odi.labor.aso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mn.odi.labor.entities.common.User;

public class LoginState implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	private String activeMenu = "";

	private User user = null;

	private String selectedModuleName;

	private List<String> roleNames;

	private String orgName;

	private String depName;

	public ArrayList<Long> orgIdList;

	/*
	 * METHODS
	 */

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;

		if (user.getOrg() != null)

			orgName = user.getOrg().getName();
		else
			orgName = "";

		if (user.getDepartment() != null) {
			depName = user.getDepartment().getName();
		} else {
			depName = "";
		}
	}

	public String getSelectedModuleName() {
		if (this.selectedModuleName == null)
			this.selectedModuleName = "";

		return this.selectedModuleName;
	}

	public void setSelectedModuleName(String selectedModuleName) {
		this.selectedModuleName = selectedModuleName;
	}

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = new ArrayList<String>();

		for (String roleName : roleNames) {
			this.roleNames.add(roleName.toLowerCase());
		}
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	/*
	 * BUSINESS METHODS
	 */

	public boolean hasRole(String roleName) {
		if (roleNames == null) {
			return false;
		}

		return roleNames.contains(roleName.toLowerCase());
	}

	public ArrayList<Long> getOrgIdList() {
		return orgIdList;
	}

	public void setOrgIdList(ArrayList<Long> heOrgIdList) {
		this.orgIdList = heOrgIdList;
	}

	public String getActiveMenu() {
		return activeMenu;
	}

	public void setActiveMenu(String activeMenu) {
		this.activeMenu = activeMenu;
	}
}
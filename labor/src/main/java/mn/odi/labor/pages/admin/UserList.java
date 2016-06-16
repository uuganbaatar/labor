package mn.odi.labor.pages.admin;

import java.util.List;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.enums.RoleEnum;

public class UserList {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<User> userList;

	@Property
	private User userRow;

	@Property
	@Persist
	private String firstname;

	private int rowIndex;

	@Property
	@Persist
	private String lastname;

	@Property
	@Persist
	private User user;

	@Property
	@Persist
	private Organization org;

	@Property
	@Persist
	private RoleEnum role;

	@Property
	@Persist
	private String phone;

	@Property
	@Persist
	private String email;

	@InjectPage
	private UserCreate createPage;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("user");
		loginState.setPageTitle(message.get("user"));
		userList = dao.getUserList();

		rowIndex = 1;
	}

	public int getRowIndex() {
		return rowIndex++;
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public Object onActionFromUserCreate() {
		createPage.onActivate(null);
		return createPage;
	}

	public void onSuccess() {
		System.out.println("[Lastname:]" + lastname);
		System.out.println("[Firstname:]" + firstname);
	}

	public Object onActionFromEditUser(User u) {
		createPage.onActivate(u);
		return createPage;
	}

}
package mn.odi.labor.pages.admin;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.enums.RoleEnum;
import mn.odi.labor.util.Constants;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

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

	@Persist
	@Property
	private String lname;

	@Persist
	@Property
	private String fname;

	@Inject
	private AlertManager alertManager;

	@Inject
	private Messages messages;

	@Persist
	@Property
	private String mail;

	@Persist
	@Property
	private Date d1;

	@Persist
	@Property
	private Date d2;

	@Persist
	@Property
	private Boolean active;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("user");
		loginState.setPageTitle(message.get("user"));
		userList = dao.getUserListSearch(lname, fname, mail, d1, d2, active);

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

	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
		lname = null;
		fname = null;
		mail = null;
		d1 = null;
		d2 = null;
		active = null;
	}

	public Format getDateFormat() {
		return new SimpleDateFormat(Constants.DATE_FORMAT);
	}

	@CommitAfter
	Object onActionFromUserDelete(User u) {
		dao.deleteObject(u);
		u = new User();
		userList = dao.getUserList();
		return UserList.class;
	}

	@CommitAfter
	Object onActionFromChangepass(User u) {
		try {
			u.setPassword(u.getUsername());
			dao.updateObject(u);
			alertManager.alert(Duration.SINGLE, Severity.SUCCESS,
					messages.get("successPassword"));
			return UserList.class;

		} catch (Exception e) {

			alertManager.alert(Duration.SINGLE, Severity.WARN,
					messages.get("cannotDeleteMessage"));
			return UserList.class;
		}
	}

}
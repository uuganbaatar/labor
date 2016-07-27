package mn.odi.labor.pages.admin;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.util.EnumSelectModel;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.Organization;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.enums.RoleEnum;
import mn.odi.labor.models.CommonSM;

public class UserCreate {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private Request request;

	@Inject
	private RequestGlobals requestGlobals;

	@Inject
	private Response response;

	@Inject
	private Context context;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private User user;

	@InjectPage
	private UserList listPage;

	public void onActivate(User u) {
		if (u != null)
			this.user = u;
		else
			user = new User();
	}

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("user");
		loginState.setPageTitle(message.get("userCreate"));
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public SelectModel getRoleSelectModel() {
		EnumSelectModel em = new EnumSelectModel(RoleEnum.class, message);
		return em;
	}

	public SelectModel getOrgSelectModel() {
		CommonSM<Organization> sm = new CommonSM<Organization>(
				Organization.class, dao.getOrgList(), "getName");
		return sm;
	}

	@DiscardAfter
	Object onCancel() {
		return listPage;
	}

	@CommitAfter
	Object onSubmit() {
		user.setUsername(user.getEmail());
		user.setPassword(user.getEmail());
		dao.saveOrUpdateObject(user);
		return listPage;
	}

}
package mn.odi.labor.pages.admin;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;

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

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("hyanah");
		loginState.setPageTitle(message.get("dashboard"));
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

}
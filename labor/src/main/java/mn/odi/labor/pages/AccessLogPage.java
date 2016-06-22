package mn.odi.labor.pages;

import java.util.Date;
import java.util.List;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.AccessLog;
import mn.odi.labor.entities.common.User;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import se.unbound.tapestry.breadcrumbs.BreadCrumb;
import se.unbound.tapestry.breadcrumbs.BreadCrumbReset;

@BreadCrumb(titleKey = "userProfile")
@BreadCrumbReset(ignorePages = { Object.class })
@Import(stylesheet = { "context:css/home.css", "context:css/menu.css",
		"context:css/jquery-ui.css", }, library = { "context:js/custom.js" })
public class AccessLogPage {

	@SessionState
	private LoginState loginState;

	/*
	 * INJECTS
	 */

	@Inject
	private SccDAO sccDAO;

	@Inject
	private Messages messages;

	@Inject
	private AlertManager manager;

	@Inject
	private Request request;

	@Inject
	private JavaScriptSupport javaScriptSupport;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private Context context;

	@Property
	@Persist
	private User user;

	@Inject
	private SccDAO dao;

	@Inject
	private AlertManager alertManager;

	@Property
	@Persist
	private List<AccessLog> accList;

	@Property
	private AccessLog accRow;

	private int number;

	@InjectComponent
	private Grid grid;

	@Persist
	@Property
	private Date d1;

	@Persist
	@Property
	private Date d2;

	@Persist
	@Property
	private String lname;

	@Persist
	@Property
	private String fname;

	void setupRender() {

		accList = dao.getAccessLogsSearch(lname, fname, d1, d2);
		System.err.println(user);
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}


	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
		lname = null;
		fname = null;
		d1 = null;
		d2 = null;
	}

	public String getUsername() {
		String name = "-";
		if (loginState != null && loginState.getUser() != null) {
			if (loginState.getUser().getLastname() != null) {
				name = loginState.getUser().getLastname();
			}
			if (loginState.getUser().getFirstname() != null) {
				name = name + "-" + loginState.getUser().getFirstname();
			}
		}
		return name;

	}

}
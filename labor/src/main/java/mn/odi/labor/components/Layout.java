package mn.odi.labor.components;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.User;
import mn.odi.labor.pages.Index;
import mn.odi.labor.pages.emp.EmpListPage;
import mn.odi.labor.pages.job.JobPage;
import mn.odi.labor.pages.admin.UserList;

@Import(stylesheet = { "context:assets/css/bootstrap.min.css", "context:assets/css/core.css",
		"context:assets/css/components.css", "context:assets/css/icons.css", "context:assets/css/pages.css",
		"context:assets/css/responsive.css", "context:assets/css/responsive.css" }, library = {
				"context:assets/js/modernizr.min.js", "context:assets/js/jquery.min.js",
				"context:assets/js/bootstrap.min.js", "context:assets/js/detect.js", "context:assets/js/fastclick.js",
				"context:assets/js/jquery.slimscroll.js", "context:assets/js/jquery.blockUI.js",
				"context:assets/js/waves.js", "context:assets/js/wow.min.js", "context:assets/js/jquery.nicescroll.js",
				"context:assets/js/jquery.scrollTo.min.js", "context:assets/js/jquery.core.js" })
public class Layout {

	@SessionState
	private LoginState loginState;

	@Inject
	private JavaScriptSupport javaScriptSupport;

	@Inject
	private RequestGlobals requestGlobals;

	@Inject
	private Response response;

	@Inject
	private ComponentResources resources;

	@Inject
	private Context context;

	@Inject
	private Messages message;

	@Inject
	private SccDAO sccDAO;

	@InjectPage
	private Index indexpage;

	@InjectPage
	private JobPage jobpage;

	@InjectPage
	private EmpListPage emppage;

	@InjectPage
	private UserList userListpage;

	void beginRender() {
		if (loginState.getUser() == null) {

			SecurityContext ctx = SecurityContextHolder.getContext();

			Authentication auth = ctx.getAuthentication();

			UserDetails userDetails = (UserDetails) (auth.getPrincipal());

			User user = (User) this.sccDAO.getUserByUsername(userDetails.getUsername());

			if (user == null) {
				throw new UsernameNotFoundException(

						"User not found in database");
			}

			loginState.setUser(user);

			user.setLastAccessDate(Calendar.getInstance().getTime());

			this.sccDAO.saveOrUpdateObject(user);

			loginState.setRoleNames(user.getRoleNames());
		}
	}

	public User getUser() {
		return loginState.getUser();
	}

	void onActionFromLogout() throws IOException {
		HttpSession session = requestGlobals.getHTTPServletRequest().getSession(false);

		if (session != null) {
			session.invalidate();
		}

		response.sendRedirect("./login");
	}

	public String getSelectedTabJob() {
		return (loginState.getActiveMenu() == "job") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromJobTab() {
		return jobpage;
	}

	public String getSelectedTabEmployer() {
		return (loginState.getActiveMenu() == "emp") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromemployerTab() {
		return emppage;
	}

	public String getSelectedTabReport() {
		return (loginState.getActiveMenu() == "report") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromReportTab() {
		return indexpage;
	}

	public String getSelectedTabHyanah() {
		return (loginState.getActiveMenu() == "hyanah") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromHyanahTab() {
		return indexpage;
	}

	public String getSelectedTabUser() {
		return (loginState.getActiveMenu() == "user") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromUserTab() {
		return userListpage;
	}

	public String getPageTitle() {
		if (loginState.getPageTitle() != null)
			return loginState.getPageTitle();
		else
			return "";
	}
}
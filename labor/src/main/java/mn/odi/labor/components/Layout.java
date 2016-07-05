package mn.odi.labor.components;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
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
import mn.odi.labor.enums.RoleEnum;
import mn.odi.labor.pages.Index;
import mn.odi.labor.pages.admin.LavlahGeneralType;
import mn.odi.labor.pages.admin.UserList;
import mn.odi.labor.pages.emp.EmpListPage;
import mn.odi.labor.pages.job.JobPage;
import mn.odi.labor.pages.labor.HudulmurReportPage;
import mn.odi.labor.pages.labor.HudulmurReportPage2;
import mn.odi.labor.pages.labor.HudulmurReportPage3;
import mn.odi.labor.pages.labor.HudulmurReportPage4;
import mn.odi.labor.pages.labor.OrgListPage;
import mn.odi.labor.pages.organization.LaborReportOrgList;

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

	@InjectPage
	private LavlahGeneralType lavlahListpage;

	@InjectPage
	private LaborReportOrgList reportListpage;

	@InjectPage
	private OrgListPage orgPage;

	@InjectPage
	private HudulmurReportPage helreportPage;

	@InjectPage
	private HudulmurReportPage2 helreportPage2;

	@InjectPage
	private HudulmurReportPage3 helreportPage3;

	@InjectPage
	private HudulmurReportPage4 helreportPage4;

	@Property
	private boolean isAdmin, isUser, isLabUser;

	@CommitAfter
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

			System.err.println("get user=" + loginState.getUser());

			user.setLastAccessDate(Calendar.getInstance().getTime());

			this.sccDAO.saveOrUpdateObject(user);

			loginState.setRoleNames(user.getRoleNames());
		}

		System.err.println(loginState.getUser().getCurrentrole().name());
		if (loginState.getUser().getCurrentrole() == RoleEnum.ADMIN) {
			isAdmin = true;
		} else if (loginState.getUser().getCurrentrole() == RoleEnum.USER) {
			isUser = true;
		} else {
			isLabUser = true;
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
		return reportListpage;
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

	public String getSelectedTabLavlah() {
		return (loginState.getActiveMenu() == "lavlah") ? "waves-effect subdrop" : "waves-effect";
	}

	public String getSelectedOrgLavlah() {
		return (loginState.getActiveMenu() == "org") ? "waves-effect subdrop" : "waves-effect";
	}

	public String getSelectedhelReport() {
		return (loginState.getActiveMenu() == "heltes") ? "waves-effect subdrop" : "waves-effect";
	}

	public String getSelectedhelReport2() {
		return (loginState.getActiveMenu() == "heltes2") ? "waves-effect subdrop" : "waves-effect";
	}

	public String getSelectedhelReport3() {
		return (loginState.getActiveMenu() == "heltes3") ? "waves-effect subdrop" : "waves-effect";
	}

	public String getSelectedhelReport4() {
		return (loginState.getActiveMenu() == "heltes4") ? "waves-effect subdrop" : "waves-effect";
	}

	public String getSelectedhelReportMain() {
		return (loginState.getActiveMenu().contains("heltes")) ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromUserTab() {
		return userListpage;
	}

	public Object onActionFromLavlahTab() {
		return lavlahListpage;
	}

	public Object onActionFromOrgTab() {
		return orgPage;
	}

	public Object onActionFromHelReportTab() {
		return helreportPage;
	}

	public Object onActionFromHelReportTab2() {
		return helreportPage2;
	}

	public Object onActionFromHelReportTab3() {
		return helreportPage3;
	}

	public Object onActionFromHelReportTab4() {
		return helreportPage4;
	}

	public String getPageTitle() {
		if (loginState.getPageTitle() != null)
			return loginState.getPageTitle();
		else
			return "";
	}
}

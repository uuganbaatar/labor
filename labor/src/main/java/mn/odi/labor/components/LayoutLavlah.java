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
import mn.odi.labor.pages.admin.LavlahCompanyStatus;
import mn.odi.labor.pages.admin.LavlahCompanyTrend;
import mn.odi.labor.pages.admin.LavlahEmpGarsan;
import mn.odi.labor.pages.admin.LavlahGeneralType;
import mn.odi.labor.pages.admin.LavlahHelber;
import mn.odi.labor.pages.admin.LavlahHurungu;
import mn.odi.labor.pages.admin.LavlahSumDuureg;
import mn.odi.labor.pages.admin.UserList;
import mn.odi.labor.pages.emp.EmpListPage;
import mn.odi.labor.pages.job.JobPage;
import mn.odi.labor.pages.labor.OrgListPage;

@Import(stylesheet = { "context:assets/css/bootstrap.min.css", "context:assets/css/core.css",
		"context:assets/css/components.css", "context:assets/css/icons.css", "context:assets/css/pages.css",
		"context:assets/css/responsive.css", "context:assets/css/responsive.css" }, library = {
				"context:assets/js/modernizr.min.js", "context:assets/js/jquery.min.js",
				"context:assets/js/bootstrap.min.js", "context:assets/js/detect.js", "context:assets/js/fastclick.js",
				"context:assets/js/jquery.slimscroll.js", "context:assets/js/jquery.blockUI.js",
				"context:assets/js/waves.js", "context:assets/js/wow.min.js", "context:assets/js/jquery.nicescroll.js",
				"context:assets/js/jquery.scrollTo.min.js", "context:assets/js/jquery.core.js" })
public class LayoutLavlah {

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
	private LavlahCompanyTrend lavlahTrendpage;

	@InjectPage
	private LavlahHurungu lahlahhurunguPage;

	@InjectPage
	private LavlahHelber lahlahhelberPage;

	@InjectPage
	private LavlahCompanyStatus lahlahstatusPage;

	@InjectPage
	private LavlahEmpGarsan lahlahgarsanPage;

	@InjectPage
	private LavlahSumDuureg lahlahsumPage;

	@InjectPage
	private OrgListPage orgPage;

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

	public String getSelectedTabLavlah() {
		return (loginState.getActiveMenu() == "lavlah") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromUserTab() {
		return userListpage;
	}

	public Object onActionFromLavlahTab() {
		return lavlahListpage;
	}

	public String getPageTitle() {
		if (loginState.getPageTitle() != null)
			return loginState.getPageTitle();
		else
			return "";
	}

	public String getSelectedTabLavlahGeneral() {
		return (loginState.getActiveDedMenu() == "lavlahgeneral") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public String getSelectedTabLavlahTrend() {
		return (loginState.getActiveDedMenu() == "lavlahtrend") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public String getSelectedTabLavlahHurungu() {
		return (loginState.getActiveDedMenu() == "lavlahhurungu") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public String getSelectedTabLavlahHelber() {
		return (loginState.getActiveDedMenu() == "lavlahhelber") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public String getSelectedTabLavlahStatus() {
		return (loginState.getActiveDedMenu() == "lavlahstatus") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public String getSelectedTabLavlahGarsan() {
		return (loginState.getActiveDedMenu() == "lavlahgarsan") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public String getSelectedTabLavlahSum() {
		return (loginState.getActiveDedMenu() == "lavlahsum") ? "btn text-left btn-white waves-effect selected"
				: "btn text-left btn-white waves-effect";
	}

	public Object onActionFromLavlahGeneralTab() {
		return lavlahListpage;
	}

	public Object onActionFromLavlahTrendTab() {
		return lavlahTrendpage;
	}

	public Object onActionFromLavlahHurunguTab() {
		return lahlahhurunguPage;
	}

	public Object onActionFromLavlahHelberTab() {
		return lahlahhelberPage;
	}

	public Object onActionFromLavlahStatusTab() {
		return lahlahstatusPage;
	}

	public Object onActionFromLavlahGarsanTab() {
		return lahlahgarsanPage;
	}

	public Object onActionFromLavlahSumTab() {
		return lahlahsumPage;
	}

	public String getSelectedOrgLavlah() {
		return (loginState.getActiveMenu() == "org") ? "waves-effect subdrop" : "waves-effect";
	}

	public Object onActionFromOrgTab() {
		return orgPage;
	}

}
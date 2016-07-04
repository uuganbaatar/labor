
package mn.odi.labor.pages;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.dao.hibernate.SecurityDAOHibernate;
import mn.odi.labor.entities.common.AccessLog;

import org.apache.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;

@Import(stylesheet = { "context:assets/css/bootstrap.min.css", "context:assets/css/core.css",
		"context:assets/css/components.css", "context:assets/css/pages.css", "context:assets/css/icons.css",
		"context:assets/css/responsive.css" })
public class Login {

	public static final String SECURITY_LOGIN = "/j_spring_security_check";

	@Inject
	@Value("${spring-security.check.url}")
	private String checkUrl;

	@Inject
	private Request request;

	public static final String SECURITY_USERNAME = "j_username";
	public static final String SECURITY_PASSWORD = "j_password";

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Property
	private boolean rememberMe;

	@Persist(PersistenceConstants.FLASH)
	private String loginMessage;

	@Inject
	private Response response;

	@Inject
	private RequestGlobals requestGlobals;

	@Inject
	private SccDAO sccDAO;

	@Inject
	private SecurityDAOHibernate secDao;
	
	private static final Logger LOGGER = Logger.getLogger("labor");
	
	@Inject
	private AlertManager alertManager;
	
	@Inject
	private Messages messages;

	@Persist("flash")
	private boolean failed;

	public void beginRender() {
		System.out.println("[URL:]" + checkUrl);
	}

	public String getLoginCheckUrl() {
		String loginCheckUrl = request.getContextPath() + checkUrl;
		return loginCheckUrl;
	}

	public boolean isFailed() {
		return failed;
	}

	public void onSetError() {
		failed = true;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}
	/*@CommitAfter
	Object onSuccess() {
		try {
			
			System.err.println("dsfghjkl");
			//loginState.setUser(SccDAO.getUserByUsername());
			AccessLog accessLog = new AccessLog();
			accessLog.setAccessDate(sccDAO.getCurrentDate());
			accessLog.setUser(loginState.getUser());
			accessLog.setIpAddress(requestGlobals.getHTTPServletRequest().getRemoteAddr());
			sccDAO.saveOrUpdateObject(accessLog);
		} catch (Exception e) {
			LOGGER.warn("userCreatePage", e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, messages.get("invalidUser"));
		}
		return Index.class;
	}*/

}
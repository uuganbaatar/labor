package mn.odi.labor.pages;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.common.User;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import se.unbound.tapestry.breadcrumbs.BreadCrumb;
import se.unbound.tapestry.breadcrumbs.BreadCrumbReset;

import com.google.common.base.Charsets;

@BreadCrumb(titleKey = "userProfile")
@BreadCrumbReset(ignorePages = { Object.class })
@Import(stylesheet = { "context:css/home.css", "context:css/menu.css",
		"context:css/jquery-ui.css", }, library = { "context:js/custom.js" })
public class UserProfile {

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
	
	@Property
	private String currentPassword;

	@Property
	private String newPassword;

	@Property
	private String confirmPassword;
	
	@InjectComponent
	private Field fieldConfirm;
	
	@Inject
	private AlertManager alertManager;

	/*
	 * PAGE RENDER
	 */
	void setupRender() {
		user = loginState.getUser();

		System.err.println(user);
	}

	@CommitAfter
	Object onSubmit() {

		dao.saveOrUpdateObject(user);
		return UserProfile.class;
	}

	public String getOrgName() {
		String name = "-";
		if (user != null && user.getOrg() != null) {
			name = user.getOrg().getName();
		}
		return name;
	}
	
	public String getRoleName() {
		String name = "-";
		if (user != null && user.getCurrentrole() != null) {
			name = user.getCurrentrole().name();
		}
		return name;
	}
	
	@CommitAfter
	Object onSuccessFromChangePass() {
		/*String encryptedPassword = new Sha1Hash(currentPassword).toString();
		boolean hasError = false;
		if (!encryptedPassword.equals(user.getPassword())) {
			alertManager.alert(Duration.TRANSIENT, Severity.WARN,
					messages.get("incorrectOldPassword"));
			hasError = true;
		} else if (newPassword != null && !newPassword.equals(confirmPassword)) {
			alertManager.alert(Duration.TRANSIENT, Severity.WARN,
					messages.get("passwordDoesntMatch"));
			hasError = true;
		}

		if (!hasError) {
			user.setPassword(newPassword);
			dao.saveOrUpdateObject(user);
		}*/
		return UserProfile.class;
	}
	
	 public static byte[] encrypt(String x) throws Exception {
		    java.security.MessageDigest d = null;
		    d = java.security.MessageDigest.getInstance("SHA-1");
		    d.reset();
		    d.update(x.getBytes());
		    return d.digest();
		  }

}
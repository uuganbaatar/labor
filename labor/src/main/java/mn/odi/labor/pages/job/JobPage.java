package mn.odi.labor.pages.job;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import mn.odi.labor.aso.LoginState;

public class JobPage {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("job");
		loginState.setPageTitle(message.get("job"));
	}
}
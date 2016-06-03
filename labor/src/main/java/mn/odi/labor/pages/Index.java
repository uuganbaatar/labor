package mn.odi.labor.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.services.Context;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;

public class Index {

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

	public Integer getAllJobCount() {
		Integer l = dao.getAllJobs();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getNewJobCount() {
		Integer l = dao.getNewJobs();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Integer getHasagdsanJobs() {
		Integer i = 0;
		return i;
	}

	public Integer getAllEmployees() {
		Integer l = dao.getAllEmployees();
		if (l != null && l > 0) {
			return l;
		} else {
			return 0;
		}
	}

	public Object getBarData() {

		System.err.println("sdsgdsgdhsghdgs");
		JSONArray bar = new JSONArray();
		JSONArray types = new JSONArray();
		bar.put(types);
		JSONArray counts = new JSONArray();
		bar.put(counts);
		List<Object> list = new ArrayList<Object>();

		for (Object _values : list) {
			Object[] values = (Object[]) _values;
			if (values[0] != null) {
				types.put(values[0]);
				counts.put(values[1]);
			}
		}

		return bar;
	}

}
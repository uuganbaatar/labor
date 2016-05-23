package mn.odi.labor.pages.admin;

import java.util.List;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.CompanyTrend;

public class LavlahCompanyTrend {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<CompanyTrend> list;

	@Property
	private CompanyTrend row;

	@Property
	private String name;

	@InjectComponent
	private Zone listZone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("lavlah");
		loginState.setActiveDedMenu("lavlahtrend");
		loginState.setPageTitle(message.get("lavlah"));
		list = dao.getCompanyTrendList();
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	@CommitAfter
	public void onSuccess() {
		CompanyTrend obj = new CompanyTrend();
		obj.setName(name);
		dao.saveOrUpdateObject(obj);
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getCompanyTrendList();
	}

}
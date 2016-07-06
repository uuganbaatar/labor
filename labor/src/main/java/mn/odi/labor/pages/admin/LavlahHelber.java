package mn.odi.labor.pages.admin;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

import mn.odi.labor.aso.LoginState;
import mn.odi.labor.dao.SccDAO;
import mn.odi.labor.entities.admin.CompanyHelber;
import mn.odi.labor.entities.admin.GeneralType;

public class LavlahHelber {

	@SessionState
	private LoginState loginState;

	@Inject
	private Messages message;

	@Inject
	private SccDAO dao;

	@Property
	@Persist
	private List<CompanyHelber> list;

	@Property
	private CompanyHelber row;

	@Property
	private String name;

	@InjectComponent
	private Zone listZone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private AlertManager alertManager;

	@InjectComponent
	private Grid grid;

	private int number;

	@Persist
	@Property
	private Date d1;

	@Persist
	@Property
	private Date d2;

	@Persist
	@Property
	private Boolean active;

	@Persist
	@Property
	private String gname;

	@CommitAfter
	void beginRender() {
		loginState.setActiveMenu("lavlah");
		loginState.setActiveDedMenu("lavlahhelber");
		loginState.setPageTitle(message.get("lavlah"));
		if (active == null) {
			active = true;
		}
		list = dao.getLavlahHelberListSearch(gname, d1, d2, active);
	}

	public String getUserName() {
		return loginState.getUser().getFullName();
	}

	public static boolean containsWhiteSpace(final String testCode) {
		if (testCode != null) {
			for (int i = 0; i < 2; i++) {
				if (Character.isWhitespace(testCode.charAt(i))) {
					return true;
				}
			}
		}
		return false;
	}

	@CommitAfter
	public void onSuccessFromSave() {
		if (LavlahHelber.containsWhiteSpace(name)) {
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message.get("hoosonzai"));
		} else {
			if (dao.getHelberByName(name) != null) {
				alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message.get("burtgeltei"));
			} else {
				CompanyHelber obj = new CompanyHelber();
				obj.setName(name);
				dao.saveOrUpdateObject(obj);
			}
		}
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone);
		}
		list = dao.getLavlahHelberList();
	}

	public Object onActionFromDeleteObject(GeneralType obj) {
		try {
			dao.deleteObject(obj);
		} catch (Exception e) {
			System.out.println("[ERROR DELETE:]" + e);
			alertManager.alert(Duration.TRANSIENT, Severity.ERROR, message.get("deleteerror"));
		}

		return LavlahEmpGarsan.class;
	}

	public int getNumber() {
		return (grid.getCurrentPage() - 1) * grid.getRowsPerPage() + ++number;
	}

	@CommitAfter
	public Object onEnable(CompanyHelber c) {
		if (c.getIsActive() == true) {
			c.setIsActive(false);
		} else {
			c.setIsActive(true);
		}
		dao.saveOrUpdateObject(c);
		list = dao.getLavlahHelberListSearch(gname, d1, d2, active);
		ajaxResponseRenderer.addRender(listZone);
		return LavlahHelber.class;
	}

	@CommitAfter
	Object onSuccessFromSearch() {
		return null;
	}

	@OnEvent(value = "cancel")
	void reset() {
		gname = null;
		d1 = null;
		d2 = null;
		active = null;
	}
}